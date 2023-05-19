package com.disenio.rigormorty.service;

import com.disenio.rigormorty.dto.ClienteRegistroDTO;
import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.enums.FormaPago;
import com.disenio.rigormorty.exception.CustomException;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import com.disenio.rigormorty.repository.RegistroCompraRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegistroCompraServiceImpl implements RegistroCompraService {

    private final RegistroCompraRepository registroCompraRepository;
    private final ClienteService clienteService;
    private final ParcelaService parcelaService;
    private final ModelMapper mapper;

    @Override
    public RegistroCompraResponse addRegistroCompra(RegistroCompra registroCompra) {

        registroCompra.setPago(Date.from(Instant.now()));
        registroCompra.setVencimiento(getVencimiento(1));

        validarFormaPago(registroCompra.getFormaPago());

        ClienteRegistroDTO clienteRegistroDTO = this.mapper.map(clienteService.getById(registroCompra.getCliente().getId()), ClienteRegistroDTO.class);

        asignarClienteParcelas(registroCompra, clienteRegistroDTO);

        RegistroCompra newRegistro = registroCompraRepository.save(registroCompra);

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
    public Object updateRegistroCompra(RegistroCompra registroCompra) {
        Optional<RegistroCompra> optionalRegistro = registroCompraRepository.findById(registroCompra.getId());
        if (optionalRegistro.isPresent()) {
            RegistroCompra registroToUpdate = optionalRegistro.get();

            registroToUpdate.setEntrega(registroCompra.getEntrega());
            registroToUpdate.setTotalCuotas(registroCompra.getTotalCuotas());
            registroToUpdate.setCuotasPagas(registroCompra.getCuotasPagas());
            registroToUpdate.setVencimiento(registroCompra.getVencimiento());
            registroToUpdate.setPago(registroCompra.getPago());
            registroToUpdate.setFormaPago(registroCompra.getFormaPago());
            registroToUpdate.setCliente(registroCompra.getCliente());
            registroToUpdate.setParcelas(registroCompra.getParcelas());
            registroToUpdate.setTotalPagar(registroCompra.getTotalPagar());

            registroCompraRepository.save(registroToUpdate);

            return registroToUpdate;
        } else {
            throw new ResourceNotFoundException("Registro de compra no encontrado");
        }
    }

    @Override
    public RegistroCompraResponse pagoCuota(Long id, Integer cantidad) {
        Optional<RegistroCompra> registroCompra = registroCompraRepository.findById(id);

        if (registroCompra.isPresent()) {
            RegistroCompra registroCompraUpdate = registroCompra.get();

            registroCompraUpdate.setCuotasPagas(registroCompraUpdate.getCuotasPagas() + cantidad);
            registroCompraUpdate.setFormaPago(registroCompraUpdate.getFormaPago());

            registroCompraUpdate.setVencimiento(getVencimiento(cantidad));
            registroCompraUpdate.setPago(Date.from(Instant.now()));

            registroCompraRepository.save(registroCompraUpdate);

            return this.mapper.map(registroCompraUpdate, RegistroCompraResponse.class);
        } else {
            throw new ResourceNotFoundException("Registro compra no encontrado");
        }
    }

    private Date getVencimiento(Integer cantidad) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(Instant.now()));
        calendar.add(Calendar.MONTH, cantidad);

        return calendar.getTime();
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
}
