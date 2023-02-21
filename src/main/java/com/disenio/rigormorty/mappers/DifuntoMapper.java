package com.disenio.rigormorty.mappers;

import com.disenio.rigormorty.dto.DifuntoDTO;
import com.disenio.rigormorty.entity.Difunto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DifuntoMapper {

    public static DifuntoDTO entityToDTO(Difunto difunto){
        DifuntoDTO difuntoDTO = new DifuntoDTO();

        difuntoDTO.setId(difunto.getId());
        difuntoDTO.setNombre(difunto.getNombre());
        difuntoDTO.setApellido(difunto.getApellido());
        difuntoDTO.setDni(difunto.getDni());
        difuntoDTO.setActa(difunto.getActa());
        difuntoDTO.setNumExpediente(difunto.getNumExpediente());
        difuntoDTO.setFechaDef(difunto.getFechaDef());
        difuntoDTO.setFechaNac(difunto.getFechaNac());

        return difuntoDTO;
    }

    public static List<DifuntoDTO> entityToDTOList(List<Difunto> difuntos){
        return difuntos.stream().map(DifuntoMapper::entityToDTO).collect(Collectors.toList());
    }
}
