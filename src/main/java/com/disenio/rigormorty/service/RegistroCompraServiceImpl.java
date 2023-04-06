package com.disenio.rigormorty.service;



import com.disenio.rigormorty.dto.ClienteRegistroDTO;
import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.dto.RegistroDTO;
import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.RegistroCompraRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistroCompraServiceImpl implements RegistroCompraService{
    private final RegistroCompraRepository registroCompraRepository;

    private final  ClienteService clienteService;

    private final ParcelaService parcelaService;

    private final ModelMapper mapper;

    @Override
    public ResponseEntity<RegistroDTO> addRegistroCompra(RegistroCompra registroCompra){

        Cliente cliente = clienteService.getById(registroCompra.getCliente().getId());
        ClienteRegistroDTO clienteRegistroDTO = this.mapper.map(cliente, ClienteRegistroDTO.class);

        registroCompra.getParcelas().forEach(parcela -> parcela.setCliente(this.mapper.map(clienteRegistroDTO,Cliente.class)));

        List<ParcelaDTO> parcelasDTO = Collections.singletonList(this.mapper.map(registroCompra.getParcelas(), ParcelaDTO.class));

        RegistroCompra newRegistro = registroCompraRepository.save(registroCompra);


        parcelasDTO.forEach(parcelaService::updateParcelaRegistro);

        return ResponseEntity.ok(this.mapper.map(newRegistro,RegistroDTO.class));
    }

    @Override
    public ResponseEntity<List<RegistroCompra>> getRegistroCompras(){
        List<RegistroCompra> registroCompras = registroCompraRepository.findAll();
        return ResponseEntity.ok(registroCompras);
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



}
