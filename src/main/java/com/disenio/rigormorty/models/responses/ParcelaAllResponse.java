package com.disenio.rigormorty.models.responses;

import com.disenio.rigormorty.entity.EstadoParcela;

import java.util.List;

public interface ParcelaAllResponse {
    Long getId();
    Integer getNivelMax();
    String getNumeroParcela();
    List<EstadoParcela> getEstados();
}
