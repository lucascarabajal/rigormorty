package com.disenio.rigormorty.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class VentasResponses implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private UserVentasRest usuario;
    private LocalDate fechaCompra;
    private Double entrega;
    private Double totalPagar;
}
