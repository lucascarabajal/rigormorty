package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.Periodo;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodoServiceImpl implements PeriodoService{
    private final PeriodoRepository periodoRepository;

    @Autowired
    public PeriodoServiceImpl(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    @Override
    public ResponseEntity<Periodo> addPeriodo(Periodo periodo){
        Periodo newPeriodo = periodoRepository.save(periodo);

        return ResponseEntity.ok(newPeriodo);
    }

    @Override
    public ResponseEntity<List<Periodo>> getPeriodos(){
        List<Periodo> periodos = periodoRepository.findAll();
        return ResponseEntity.ok(periodos);
    }

    @Override
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
