package com.disenio.rigormorty.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ZonaAllResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nombreZona;

    private Integer cantidadParcela;

    private Double precioZona;

    private Integer nivelMax;

    private List<ParcelaClienteResponse> parcelas;
}