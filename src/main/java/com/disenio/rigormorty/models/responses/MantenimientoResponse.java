package com.disenio.rigormorty.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MantenimientoResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;
    private Double pago;
    private String periodo;
    private ClienteAddResponse cliente;
    private ParcelaClienteResponse parcela;

}
