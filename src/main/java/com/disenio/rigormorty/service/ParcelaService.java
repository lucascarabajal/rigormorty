package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelaService {
    private final ParcelaRepository parcelaRepository;

    @Autowired
    public ParcelaService(ParcelaRepository parcelaRepository) {
        this.parcelaRepository = parcelaRepository;
    }


    public ResponseEntity<Parcela> addParcela(Parcela parcela){
        Parcela newParcela = parcelaRepository.save(parcela);

        return ResponseEntity.ok(newParcela);
    }

    public List<Parcela> getParcela(){
        List<Parcela> parcelas = parcelaRepository.findAll();

        return parcelas;
    }

    public Object updateParcela(Parcela parcela){
        Optional<Parcela> optionalParcela = parcelaRepository.findById(parcela.getId());
        if(optionalParcela.isPresent()){
            Parcela parcelaToUpdate = optionalParcela.get();

            parcelaToUpdate.setNivelActual(parcela.getNivelActual());
            parcelaToUpdate.setNumeroParcela(parcela.getNumeroParcela());
            parcelaToUpdate.setDifuntos(parcela.getDifuntos());
            parcelaToUpdate.setEstados(parcela.getEstados());
            parcelaToUpdate.setMantenimientos(parcela.getMantenimientos());
            parcelaToUpdate.setRegistros(parcela.getRegistros());
            parcelaToUpdate.setCliente(parcela.getCliente());
            parcelaToUpdate.setZona(parcela.getZona());


            parcelaRepository.save(parcelaToUpdate);

            return parcelaToUpdate;
        }else{
            throw new ResourceNotFoundException("Parcela no encontrada");
        }


    }
}
