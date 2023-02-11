package com.disenio.rigormorty.mappers;

import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Parcela;

public interface ParcelaMapper{

  static ParcelaDTO entityToDTO(Parcela parcela){
        ParcelaDTO parcelaDTO = new ParcelaDTO();

        parcelaDTO.setId(parcela.getId());
        parcelaDTO.setNumeroParcela(parcela.getNumeroParcela());
        parcelaDTO.setNivelActual(parcela.getNivelActual());
        parcelaDTO.setEstados(parcela.getEstados());
        parcelaDTO.setDifuntos(parcela.getDifuntos());
        parcelaDTO.setMantenimientos(parcela.getMantenimientos());
        parcelaDTO.setRegistros(parcela.getRegistros());

        return parcelaDTO;
    }
}
