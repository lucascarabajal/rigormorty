package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Periodo;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodoService {
    private final PeriodoRepository periodoRepository;

    @Autowired
    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    public ResponseEntity<Periodo> addPeriodo(Periodo periodo){
        Periodo newPeriodo = periodoRepository.save(periodo);

        return ResponseEntity.ok(newPeriodo);
    }

    public List<Periodo> getPeriodo(){
        List<Periodo> periodos = periodoRepository.findAll();

        return periodos;
    }

    public Object updatePeriodo(Periodo periodo){
        Optional<Periodo> optionalPeriodo = periodoRepository.findById(periodo.getId());
        if(optionalPeriodo.isPresent()){
            Periodo periodoToUpdate = optionalPeriodo.get();

            periodoToUpdate.setPago(periodo.getPago());
            periodoToUpdate.setMantenimiento(periodo.getMantenimiento());

            periodoRepository.save(periodoToUpdate);

            return periodoToUpdate;
        }else{
            throw new ResourceNotFoundException("Periodo no encontrado");
        }


    }
}
