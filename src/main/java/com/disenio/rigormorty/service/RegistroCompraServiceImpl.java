package com.disenio.rigormorty.service;



import com.disenio.rigormorty.dto.ClienteRegistroDTO;
import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.enums.FormaPago;
import com.disenio.rigormorty.enums.NombreParcela;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.responses.ClienteAddResponse;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import com.disenio.rigormorty.repository.RegistroCompraRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegistroCompraServiceImpl implements RegistroCompraService{
    private final RegistroCompraRepository registroCompraRepository;

    private final  ClienteService clienteService;

    private final ParcelaService parcelaService;

    private final ModelMapper mapper;

    @Override
    public RegistroCompraResponse addRegistroCompra(RegistroCompra registroCompra){

        registroCompra.setPago(Date.from(Instant.now()));
        registroCompra.setVencimiento(obtenerVencimiento(1));

        FormaPago.valueOf(registroCompra.getFormaPago().toUpperCase());

        ClienteAddResponse cliente = clienteService.getById(registroCompra.getCliente().getId());
        ClienteRegistroDTO clienteRegistroDTO = this.mapper.map(cliente, ClienteRegistroDTO.class);

        registroCompra.getParcelas().forEach(parcela -> parcela.setCliente(this.mapper.map(clienteRegistroDTO,Cliente.class)));
        registroCompra.getParcelas().forEach(parcela -> parcela.setAsignada(true));

        List<ParcelaDTO> parcelasDTO = registroCompra.getParcelas().stream().map(parcela -> this.mapper.map(parcela, ParcelaDTO.class)).toList();

        RegistroCompra newRegistro = registroCompraRepository.save(registroCompra);


        parcelasDTO.forEach(parcelaService::updateParcelaRegistro);

        return this.mapper.map(newRegistro,RegistroCompraResponse.class);
    }

    @Override
    public List<RegistroCompraResponse> getRegistroCompras(){
        List<RegistroCompra> registroCompras = registroCompraRepository.findAll();

        return registroCompras.stream()
                .map(registroCompra -> this.mapper.map(registroCompra,RegistroCompraResponse.class)).toList();
    }

    @Override
    public Object updateRegistroCompra(RegistroCompra registroCompra){
        Optional<RegistroCompra> optionalRegistro = registroCompraRepository.findById(registroCompra.getId());
        if(optionalRegistro.isPresent()){
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
        }else{
            throw new ResourceNotFoundException("Registro de compra no encontrado");
        }
    }

    @Override
    public RegistroCompraResponse pagoCuota(Long id, Integer cantidad) {
        Optional<RegistroCompra> registroCompra = registroCompraRepository.findById(id);

        if(registroCompra.isPresent()){
            RegistroCompra registroCompraUpdate = registroCompra.get();

            registroCompraUpdate.setCuotasPagas(registroCompraUpdate.getCuotasPagas()+cantidad);
            registroCompraUpdate.setFormaPago(registroCompraUpdate.getFormaPago());

            registroCompraUpdate.setVencimiento(obtenerVencimiento(cantidad));
            registroCompraUpdate.setPago(Date.from(Instant.now()));

            registroCompraRepository.save(registroCompraUpdate);

            return this.mapper.map(registroCompraUpdate,RegistroCompraResponse.class);
        }else {
            throw new ResourceNotFoundException("Registro compra no encontrado");
        }
    }

    private Date obtenerVencimiento(Integer cantidad){
        Date proximoVenc = new SimpleDateFormat("yyyy-MM-dd").get2DigitYearStart();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(Instant.now()));
        calendar.add(Calendar.MONTH,cantidad);

        return calendar.getTime();
    }

    public List<RegistroCompraResponse> getRegistroCompraByCliente(Integer dni){
        List<RegistroCompra> registroCompra = registroCompraRepository.getRegistroComprasByClienteDni(dni);

        registroCompra.forEach(registroCompra1 -> {
            if (registroCompra1.getParcelas().isEmpty()) throw new RuntimeException("El cliente no posee parcelas");
        });

        if (registroCompra.isEmpty()) throw new RuntimeException("El cliente no posee parcelas");

        return registroCompra.stream().map(registroCompra1 -> this.mapper.map(registroCompra1,RegistroCompraResponse.class)).collect(Collectors.toList());
    }

    public void desvincularCliente(Long idParcela){
        ParcelaDTO parcela = parcelaService.getById(idParcela);

        if (!parcela.getAsignada()) throw new RuntimeException("La parcela seleccionada no tiene asociado un cliente");

        List<RegistroCompra>  registroCompra = registroCompraRepository.getRegistroComprasByClienteDni(parcela.getCliente().getDni());

        for (RegistroCompra registro : registroCompra) {
            for (Parcela parcela1 : registro.getParcelas()) {
                if (parcela1.getId().equals(idParcela)) {
                    if (parcela1.getEstados().stream().allMatch(estadoParcela -> estadoParcela.getEstadoParcela().equals(NombreParcela.ESTADO_PARCELA_COMPRADO))){
                        parcela1.setAsignada(false);
                        parcela1.getEstados().forEach(estadoParcela -> estadoParcela.setEstadoParcela(NombreParcela.ESTADO_PARCELA_LIBRE));
                        registro.getParcelas().remove(parcela1);
                        parcelaService.updateParcela(this.mapper.map(parcela1, Parcela.class));
                    }else {
                        throw new RuntimeException("No se puede desvincular");
                    }
                }
            }
        }
    }
}
