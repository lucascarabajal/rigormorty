package com.disenio.rigormorty.service;


import com.disenio.rigormorty.dto.DifuntoDTO;
import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.entity.EstadoParcela;
import com.disenio.rigormorty.enums.NombreParcela;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.DifuntoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DifuntoServiceImpl implements DifuntoService{
    private final DifuntoRepository difuntoRepository;
    private final ParcelaService parcelaService;

    private final ModelMapper mapper;
    @Override
    public ResponseEntity<Difunto> addDifunto(Difunto difunto){

        ParcelaDTO parcela = parcelaService.getById(difunto.getParcela().getId());

        List<EstadoParcela> estadoParcelas = parcela.getEstados();

        estadoParcelas.stream()
                .filter(estadoParcela -> estadoParcela.getEstadoParcela().equals(NombreParcela.ESTADO_PARCELA_COMPRADO))
                .findFirst()
                .ifPresent(estadoParcela -> estadoParcela.setEstadoParcela(NombreParcela.ESTADO_PARCELA_OCUPADO));

        Difunto newDifunto = difuntoRepository.save(difunto);
        return ResponseEntity.ok(newDifunto);
    }

    @Override
    public ResponseEntity<List<DifuntoDTO>> getDifuntos(){
        DifuntoDTO difuntoDTO = new DifuntoDTO();
        List<Difunto> difuntos = difuntoRepository.findAll();

        return ResponseEntity.ok(Collections.singletonList(this.mapper.map(difuntos, DifuntoDTO.class)));
    }

    @Override
    public Object updateDifunto(Difunto difunto){
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
//            difuntoToUpdate.setDomicilios(difunto.getDomicilios());
            difuntoToUpdate.setActa(difunto.getActa());
            difuntoToUpdate.setParcela(difunto.getParcela());
            difuntoRepository.save(difuntoToUpdate);
            return difuntoToUpdate;
        }else{
            throw new ResourceNotFoundException("Difunto no encontrado");
        }
    }
}
