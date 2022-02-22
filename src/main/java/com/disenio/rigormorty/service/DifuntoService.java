package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.DifuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DifuntoService {
    private final DifuntoRepository difuntoRepository;

    @Autowired
    public DifuntoService(DifuntoRepository difuntoRepository) {
        this.difuntoRepository = difuntoRepository;
    }

    public ResponseEntity<Difunto> addDifunto(Difunto difunto){
        Difunto newDifunto = difuntoRepository.save(difunto);
        return ResponseEntity.ok(newDifunto);
    }


    public List<Difunto> getDifunto(){
        List<Difunto> difuntos = difuntoRepository.findAll();
        return difuntos;
    }

    public Object updateDifuntos(Difunto difunto){
        Optional<Difunto> optionalDifunto = difuntoRepository.findById(difunto.getId());
        if(optionalDifunto.isPresent()){
            Difunto difuntoToUpdate = optionalDifunto.get();
            difuntoToUpdate.setNombre(difunto.getNombre());
            difuntoToUpdate.setApellido(difunto.getApellido());
            difuntoToUpdate.setDni(difunto.getDni());
            difuntoToUpdate.setFechaNac(difunto.getFechaNac());
            difuntoToUpdate.setFechaDef(difunto.getFechaDef());
            difuntoToUpdate.setNumExpediente(difunto.getNumExpediente());
            difuntoToUpdate.setNumNivel(difunto.getNumNivel());
            difuntoToUpdate.setDomicilios(difunto.getDomicilios());
            difuntoToUpdate.setActa(difunto.getActa());
            difuntoToUpdate.setParcela(difunto.getParcela());
            difuntoRepository.save(difuntoToUpdate);
            return difuntoToUpdate;
        }else{
            throw new ResourceNotFoundException("Difunto no encontrado");
        }
    }
}
