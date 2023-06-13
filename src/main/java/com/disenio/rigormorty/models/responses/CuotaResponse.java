package com.disenio.rigormorty.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class CuotaResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double pago;
    private Integer cantCuota;
    private Integer totalCuotasPagas;
    private Integer totalCuotas;
    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;
}
