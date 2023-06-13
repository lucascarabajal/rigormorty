package com.disenio.rigormorty.models.request;

import com.disenio.rigormorty.entity.RegistroCompra;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagarCuotaRequest {
    private Double pago;
    private Integer cantCuota;
    private RegistroCompra registroCompra;
}
