package com.disenio.rigormorty.models.request;

import com.disenio.rigormorty.entity.EstadoParcela;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ParcelaRequest {

    private Long id;
    private Integer nivelActual;
    private String numeroParcela;
    private List<EstadoParcela> estadoParcelas;
}
