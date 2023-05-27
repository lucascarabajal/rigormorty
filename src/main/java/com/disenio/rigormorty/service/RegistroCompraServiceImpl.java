package com.disenio.rigormorty.service;

import com.disenio.rigormorty.dto.ClienteRegistroDTO;
import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Cuota;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.enums.FormaPago;
import com.disenio.rigormorty.exception.CustomException;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.request.RegistroCompraRequest;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import com.disenio.rigormorty.models.responses.RegistroStatsResponse;
import com.disenio.rigormorty.repository.RegistroCompraRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegistroCompraServiceImpl implements RegistroCompraService {

    private final RegistroCompraRepository registroCompraRepository;
    private  ClienteService clienteService;
    private  ParcelaService parcelaService;
    private  ModelMapper mapper;
    private  CuotaService cuotaService;

    @Override
    public RegistroCompraResponse addRegistroCompra(RegistroCompraRequest registroCompra) {

        if (registroCompra.getUsuario().isActivo()) throw new CustomException("Tu usuario no se encuentra activo");

        validarFormaPago(registroCompra.getFormaPago());

        ClienteRegistroDTO clienteRegistroDTO = this.mapper.map(clienteService.getById(registroCompra.getCliente().getId()), ClienteRegistroDTO.class);

        RegistroCompra registroModel = this.mapper.map(registroCompra, RegistroCompra.class);
        asignarClienteParcelas(registroModel, clienteRegistroDTO);

        RegistroCompra newRegistro = registroCompraRepository.save(registroModel);

        if (registroCompra.getTotalCuotas()>0) agregarCuota(newRegistro, registroCompra);

        actualizarParcelas(registroCompra.getParcelas());

        return this.mapper.map(newRegistro, RegistroCompraResponse.class);
    }

    @Override
    public List<RegistroCompraResponse> getRegistroCompras() {
        List<RegistroCompra> registroCompras = registroCompraRepository.findAll();

        return registroCompras.stream()
                .map(registroCompra -> this.mapper.map(registroCompra, RegistroCompraResponse.class)).toList();
    }

    @Override
    public RegistroCompra getById(Long id) {
        return registroCompraRepository.getById(id);
    }

    @Override
    public Object updateRegistroCompra(RegistroCompra registroCompra) {
        Optional<RegistroCompra> optionalRegistro = registroCompraRepository.findById(registroCompra.getId());
        if (optionalRegistro.isPresent()) {
            RegistroCompra registroToUpdate = optionalRegistro.get();

            registroToUpdate.setEntrega(registroCompra.getEntrega());
            registroToUpdate.setFormaPago(registroCompra.getFormaPago());
            registroToUpdate.setCliente(registroCompra.getCliente());
            registroToUpdate.setParcelas(registroCompra.getParcelas());

            registroCompraRepository.save(registroToUpdate);

            return registroToUpdate;
        } else {
            throw new ResourceNotFoundException("Registro de compra no encontrado");
        }
    }

    private LocalDate getVencimiento(Integer cantidad) {
        LocalDate localDate = LocalDate.now();
        return localDate.plusMonths(cantidad);
    }

    public List<RegistroCompraResponse> getRegistroCompraByCliente(Integer dni) {
        List<RegistroCompra> registroCompra = registroCompraRepository.getRegistroComprasByClienteDni(dni);
        if (registroCompra.isEmpty()) throw new ResourceNotFoundException("El cliente no posee parcelas");

        List<RegistroCompra> registroCompras = new ArrayList<>();

        registroCompra.forEach(registroCompra1 -> {
            List<Parcela> parcelas = new ArrayList<>();
            registroCompra1.getParcelas().forEach(parcela -> {
                if (parcela.getAsignada()) {
                    parcelas.add(parcela);
                }
            });
            registroCompra1.setParcelas(parcelas);
            registroCompras.add(registroCompra1);
        });

        return registroCompras.stream().map(registroCompra1 -> this.mapper.map(registroCompra1, RegistroCompraResponse.class)).collect(Collectors.toList());
    }

    private void validarFormaPago(String formaPago) {
        try {
            FormaPago.valueOf(formaPago.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomException("La foma de pago ingresada no es válida");
        }
    }

    private void asignarClienteParcelas(RegistroCompra registroCompra, ClienteRegistroDTO cliente) {
        registroCompra.getParcelas().forEach(parcela -> {
            parcela.setCliente(this.mapper.map(cliente, Cliente.class));
            parcela.setAsignada(true);
        });
    }

    private void actualizarParcelas(List<Parcela> parcelas) {
        List<ParcelaDTO> parcelaDTO = parcelas.stream().map(parcela -> this.mapper.map(parcela, ParcelaDTO.class)).toList();
        parcelaDTO.forEach(parcelaService::updateParcelaRegistro);
    }

    public List<RegistroStatsResponse> getRegistrosByUser(Long id){
        return registroCompraRepository.findAllByUsuarioId(id).stream().map(registroCompra -> this.mapper.map(registroCompra, RegistroStatsResponse.class)).collect(Collectors.toList());
    }

    private void agregarCuota(RegistroCompra registroCompra, RegistroCompraRequest registroCompraRequest){
        Cuota cuota = new Cuota();
        cuota.setPago(registroCompra.getEntrega());
        cuota.setCantCuota(1);
        cuota.setFechaPago(LocalDate.now());
        cuota.setFechaVencimiento(getVencimiento(1));
        cuota.setRegistroCompra(registroCompra);
        cuota.setTotalCuotasPagas(0);
        cuota.setTotalCuotas(registroCompraRequest.getTotalCuotas());
        cuotaService.add(cuota);
    }
}
