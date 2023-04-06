package com.disenio.rigormorty.models.responses;

import com.disenio.rigormorty.entity.EstadoParcela;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ParcelaClienteResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer nivelMax;
    private String numeroParcela;
    private List<EstadoParcela> estados;
}
