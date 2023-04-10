package com.disenio.rigormorty.service;



import com.disenio.rigormorty.dto.ClienteRegistroDTO;
import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.dto.RegistroDTO;
import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.responses.ClienteAddResponse;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import com.disenio.rigormorty.repository.RegistroCompraRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<RegistroDTO> addRegistroCompra(RegistroCompra registroCompra){

        registroCompra.setPago(Date.from(Instant.now()));
        registroCompra.setVencimiento(obtenerVencimiento(1));

        ClienteAddResponse cliente = clienteService.getById(registroCompra.getCliente().getId());
        ClienteRegistroDTO clienteRegistroDTO = this.mapper.map(cliente, ClienteRegistroDTO.class);


        registroCompra.getParcelas().forEach(parcela -> parcela.setCliente(this.mapper.map(clienteRegistroDTO,Cliente.class)));


        List<ParcelaDTO> parcelasDTO = registroCompra.getParcelas().stream().map(parcela -> this.mapper.map(parcela, ParcelaDTO.class)).toList();

        RegistroCompra newRegistro = registroCompraRepository.save(registroCompra);


        parcelasDTO.forEach(parcelaService::updateParcelaRegistro);

        return ResponseEntity.ok(this.mapper.map(newRegistro,RegistroDTO.class));
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
            registroToUpdate.setFormaPagos(registroCompra.getFormaPagos());
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
            registroCompraUpdate.setFormaPagos(registroCompraUpdate.getFormaPagos());

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

        return registroCompra.stream().map(registroCompra1 -> this.mapper.map(registroCompra1,RegistroCompraResponse.class)).collect(Collectors.toList());
    }


}
