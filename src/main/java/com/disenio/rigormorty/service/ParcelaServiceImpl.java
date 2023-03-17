package com.disenio.rigormorty.service;


import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.mappers.ParcelaMapper;
import com.disenio.rigormorty.repository.ParcelaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParcelaServiceImpl implements ParcelaService{
    private final ParcelaRepository parcelaRepository;


    private ModelMapper mapper;

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
            parcelaToUpdate.setCliente(this.mapper.map(parcela.getCliente(), Cliente.class));

            parcelaRepository.save(parcelaToUpdate);

            return parcelaToUpdate;
        }else{
            throw new ResourceNotFoundException("Parcela no encontrada");
        }
    }

    @Override
    public Object updateParcelaRegistro(ParcelaDTO parcela){
        Optional<Parcela> optionalParcela = parcelaRepository.findById(parcela.getId());
        if(optionalParcela.isPresent()){
            Parcela parcelaToUpdate = optionalParcela.get();

            parcelaToUpdate.setNumeroParcela(parcela.getNumeroParcela());
            parcelaToUpdate.setEstados(parcela.getEstados());
            parcelaToUpdate.setMantenimientos(parcela.getMantenimientos());
            parcelaToUpdate.setCliente(this.mapper.map(parcela.getClienteRegistroDTO(), Cliente.class));

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
