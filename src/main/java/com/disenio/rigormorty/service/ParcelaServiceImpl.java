package com.disenio.rigormorty.service;


import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.*;
import com.disenio.rigormorty.enums.NombreParcela;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.responses.ParcelaClienteResponse;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import com.disenio.rigormorty.models.responses.ZonaAllResponse;
import com.disenio.rigormorty.repository.ParcelaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParcelaServiceImpl implements ParcelaService{
    private final ParcelaRepository parcelaRepository;

    private ModelMapper mapper;

    @Override
    public ResponseEntity<Parcela> addParcela(Parcela parcela){
        parcela.setAsignada(false);
        Parcela newParcela = parcelaRepository.save(parcela);
        return ResponseEntity.ok(newParcela);
    }

    @Override
    public ResponseEntity<List<ParcelaClienteResponse>> getParcelas(){
        List<Parcela> parcelas = parcelaRepository.findAll();
        List<ParcelaClienteResponse> response = parcelas.stream().map(parcela -> mapper.map(parcela,ParcelaClienteResponse.class)).toList();
        return ResponseEntity.ok(response);
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
            parcelaToUpdate.setCliente(parcela.getCliente());
            parcelaToUpdate.setAsignada(parcela.getAsignada());

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
            parcelaToUpdate.setCliente(this.mapper.map(parcela.getCliente(), Cliente.class));
            parcelaToUpdate.setAsignada(parcela.getAsignada());

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
            return this.mapper.map(parcela, ParcelaDTO.class);
        }else {
            throw new ResourceNotFoundException("No existe parcela con ese id");
        }
    }

    public List<ParcelaClienteResponse>  getParcelasByCliente(Long idCliente){

        List<Parcela> parcelas = parcelaRepository.getParcelasByCliente_IdAndAsignadaTrue(idCliente);
        if (parcelas.isEmpty())throw new RuntimeException("El cliente no tiene parcelas a su nombre");
        return parcelas.stream().map(parcela -> mapper.map(parcela, ParcelaClienteResponse.class)).collect(Collectors.toList());
    }

    public List<ParcelaClienteResponse> getParcelasDesocupadas(Long idZona){
        List<Parcela> parcelas = parcelaRepository.getParcelasLibres(idZona);

        return parcelas.stream().map(parcela -> this.mapper.map(parcela,ParcelaClienteResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Parcela getParcelaByDifunto(Long id) {
        Parcela parcela = parcelaRepository.getParcelaByDifunto(id);
        if (Objects.isNull(parcela))throw new RuntimeException("No existe parcela");
        return parcela;
    }

}
