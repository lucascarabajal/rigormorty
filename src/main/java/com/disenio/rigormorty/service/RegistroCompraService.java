package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.RegistroCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroCompraService {
    private final RegistroCompraRepository registroCompraRepository;

    @Autowired
    public RegistroCompraService(RegistroCompraRepository registroCompraRepository) {
        this.registroCompraRepository = registroCompraRepository;
    }

    public ResponseEntity<RegistroCompra> addRegistroCompra(RegistroCompra registroCompra){
        RegistroCompra newRegistro = registroCompraRepository.save(registroCompra);

        return ResponseEntity.ok(newRegistro);
    }

    public List<RegistroCompra> getRegistroCompra(){
        List<RegistroCompra> registros = registroCompraRepository.findAll();

        return registros;
    }

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
            registroToUpdate.setParcela(registroCompra.getParcela());

            registroCompraRepository.save(registroToUpdate);

            return registroToUpdate;
        }else{
            throw new ResourceNotFoundException("Registro de compra no encontrado");
        }


    }

}
