package com.disenio.rigormorty.service;


import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.mappers.ParcelaMapper;
import com.disenio.rigormorty.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParcelaServiceImpl implements ParcelaService{
    private final ParcelaRepository parcelaRepository;

    @Autowired
    public ParcelaServiceImpl(ParcelaRepository parcelaRepository) {
        this.parcelaRepository = parcelaRepository;
    }

    @Override
    public ResponseEntity<Parcela> addParcela(Parcela parcela){
        Parcela newParcela = parcelaRepository.save(parcela);

        return ResponseEntity.ok(newParcela);
    }

    @Override
    public ResponseEntity<List<Parcela>> getParcelas(){
        List<Parcela> parcelas = parcelaRepository.findAll();
        return ResponseEntity.ok(parcelas);
    }

    @Override
    public Object updateParcela(Parcela parcela){
        Optional<Parcela> optionalParcela = parcelaRepository.findById(parcela.getId());
        if(optionalParcela.isPresent()){
            Parcela parcelaToUpdate = optionalParcela.get();

            parcelaToUpdate.setNivelMax(parcela.getNivelMax());
            parcelaToUpdate.setNumeroParcela(parcela.getNumeroParcela());
            parcelaToUpdate.setDifuntos(parcela.getDifuntos());
            parcelaToUpdate.setEstados(parcela.getEstados());
            parcelaToUpdate.setMantenimientos(parcela.getMantenimientos());
            parcelaToUpdate.setRegistros(parcela.getRegistros());
//            parcelaToUpdate.setCliente(parcela.getCliente());


            parcelaRepository.save(parcelaToUpdate);

            return parcelaToUpdate;
        }else{
            throw new ResourceNotFoundException("Parcela no encontrada");
        }


    }

    @Override
    public ParcelaDTO getById(Long id) {

        Optional<Parcela> parcela = parcelaRepository.findById(id);

        if (parcela.isPresent()){
            return ParcelaMapper.entityToDTO(parcela.get());
        }else {
            throw new ResourceNotFoundException("No existe parcela con ese id");
        }
    }
}
