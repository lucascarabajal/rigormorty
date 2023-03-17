package com.disenio.rigormorty.mappers;

import com.disenio.rigormorty.dto.ClienteRegistroDTO;
import com.disenio.rigormorty.dto.DifuntoDTO;
import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.entity.Parcela;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ParcelaMapper{

    private static final ModelMapper mapper = new ModelMapper();

    public static ParcelaDTO entityToDTO(Parcela parcela){
        ParcelaDTO parcelaDTO = new ParcelaDTO();

        parcelaDTO.setId(parcela.getId());
        parcelaDTO.setNumeroParcela(parcela.getNumeroParcela());
        parcelaDTO.setNivelActual(parcela.getNivelMax());
        parcelaDTO.setEstados(parcela.getEstados());
        parcelaDTO.setMantenimientos(parcela.getMantenimientos());
    //        parcelaDTO.setRegistros(parcela.getRegistros());
        parcelaDTO.setClienteRegistroDTO(mapper.map(parcela.getCliente(), ClienteRegistroDTO.class));

        if (Objects.nonNull(parcela.getDifuntos()))
            parcelaDTO.setDifuntos(DifuntoMapper.entityToDTOList(parcela.getDifuntos()));

        return parcelaDTO;
    }

    public static List<ParcelaDTO> entityToDTOList(List<Parcela> parcelas){
        return parcelas.stream().map(ParcelaMapper::entityToDTO).collect(Collectors.toList());
    }
}
