package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cementerio;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.CementerioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CementerioService {

    @Autowired
    CementerioRepository cementerioRepository;

    public ResponseEntity<Cementerio>addCementerio(Cementerio cementerio){
        Cementerio newCementerio = cementerioRepository.save(cementerio);
        return ResponseEntity.ok(newCementerio);
    }

    public List<Cementerio> getCementerio(){
        List<Cementerio> cementerios = cementerioRepository.findAll();
        return cementerios;
    }

    public Object updateCementerio(Cementerio cementerio){
        Optional<Cementerio> optionalCementerio = cementerioRepository.findById(cementerio.getId());
        if(optionalCementerio.isPresent()){
            Cementerio cementerioToUpDate = optionalCementerio.get();
            cementerioToUpDate.setNombre(cementerio.getNombre());
            cementerioToUpDate.setCantZonas(cementerio.getCantZonas());
            cementerioToUpDate.setZonas(cementerio.getZonas());
            cementerioToUpDate.setDomicilios(cementerio.getDomicilios());
            cementerioRepository.save(cementerioToUpDate);
            return cementerioToUpDate;
        }else{
            throw new ResourceNotFoundException("Cementerio no encontrado");
        }
    }


}
