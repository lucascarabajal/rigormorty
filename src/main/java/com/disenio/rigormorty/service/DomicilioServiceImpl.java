package com.disenio.rigormorty.service;



import com.disenio.rigormorty.entity.Domicilio;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServiceImpl implements DomicilioService{

    private final DomicilioRepository domicilioRepository;

    @Autowired
    public DomicilioServiceImpl(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    public ResponseEntity<Domicilio> addDomicilio(Domicilio domicilio){
        Domicilio newDomicilio = domicilioRepository.save(domicilio);

        return ResponseEntity.ok(newDomicilio);
    }

    @Override
    public ResponseEntity<List<Domicilio>> getDomicilios(){
        List<Domicilio> domicilios = domicilioRepository.findAll();
        return ResponseEntity.ok(domicilios);
    }

    @Override
    public Object updateDomicilio(Domicilio domicilio){
        Optional<Domicilio> optionalDomicilio = domicilioRepository.findById(domicilio.getId());
        if(optionalDomicilio.isPresent()){

            Domicilio domicilioToUpdate = optionalDomicilio.get();

            domicilioToUpdate.setCalle(domicilio.getCalle());
            domicilioToUpdate.setCp(domicilio.getCp());
            domicilioToUpdate.setNumero(domicilio.getNumero());
            domicilioToUpdate.setCiudad(domicilio.getCiudad());
            domicilioToUpdate.setProvincia(domicilio.getProvincia());
            domicilioToUpdate.setPais(domicilio.getPais());
            domicilioRepository.save(domicilioToUpdate);

            return domicilioToUpdate;
        }else{
            throw new ResourceNotFoundException("Domicilio no encontrado");
        }
    }
}
