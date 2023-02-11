package com.disenio.rigormorty.models.responses;

import com.disenio.rigormorty.entity.Parcela;

import javax.persistence.Column;
import java.util.List;

public interface ZonaResponse {

    String getNombreZona();

    Integer getCantidadParcela();

    Double getPrecioZona();

    Integer getNivelMax();

    List<Parcela> getParcelas();
}
