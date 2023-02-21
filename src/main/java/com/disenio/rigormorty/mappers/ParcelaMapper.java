package com.disenio.rigormorty.mappers;

import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Parcela;

import java.util.Objects;

public class ParcelaMapper{

  public static ParcelaDTO entityToDTO(Parcela parcela){
        ParcelaDTO parcelaDTO = new ParcelaDTO();

        parcelaDTO.setId(parcela.getId());
        parcelaDTO.setNumeroParcela(parcela.getNumeroParcela());
        parcelaDTO.setNivelActual(parcela.getNivelMax());
        parcelaDTO.setEstados(parcela.getEstados());
        parcelaDTO.setMantenimientos(parcela.getMantenimientos());
        parcelaDTO.setRegistros(parcela.getRegistros());

        if (Objects.nonNull(parcela.getDifuntos()))
            parcelaDTO.setDifuntos(DifuntoMapper.entityToDTOList(parcela.getDifuntos()));


        return parcelaDTO;
  }
}
