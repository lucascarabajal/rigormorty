package com.disenio.rigormorty.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class DifuntoResponse extends PersonaResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDate fechaDef;
    private String numExpediente;
    private Integer numNivel;
    private String acta;
    private ParcelaClienteResponse parcela;

}
