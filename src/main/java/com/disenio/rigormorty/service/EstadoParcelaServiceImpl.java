package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.EstadoParcela;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.EstadoParcelaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstadoParcelaServiceImpl implements EstadoParcelaService{

    private final EstadoParcelaRepository estadoParcelaRepository;
    @Override
    public ResponseEntity<EstadoParcela> addEstadoParcela(EstadoParcela estadoParcela){
        EstadoParcela newEstadoParcela = estadoParcelaRepository.save(estadoParcela);

        return ResponseEntity.ok(newEstadoParcela);
    }

    @Override
    public ResponseEntity<List<EstadoParcela>> getEstadoParcelas(){
        List<EstadoParcela> estadoParcelas = estadoParcelaRepository.findAll();

        return ResponseEntity.ok(estadoParcelas);
    }

    @Override
    public Object updateEstadoParcela(EstadoParcela estadoParcela){
        Optional<EstadoParcela> optionalEstadoParcela = estadoParcelaRepository.findById(estadoParcela.getId());
        if(optionalEstadoParcela.isPresent()){
            EstadoParcela estadoParcelaToUpdate = optionalEstadoParcela.get();

            estadoParcelaToUpdate.setEstadoParcela(estadoParcela.getEstadoParcela());
            estadoParcelaRepository.save(estadoParcelaToUpdate);

            return estadoParcelaToUpdate;
        }else{
            throw new ResourceNotFoundException("Estado parcela no encontrado");
        }
    }


}
