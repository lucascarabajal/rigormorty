package com.disenio.rigormorty.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RegistroCompraAllCuotasResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double entrega;
    private Double totalPagar;
    private LocalDate fechaCompra;
    private LocalDate pago;
    private String formaPago;
    private ClienteAddResponse cliente;
    private List<ParcelaClienteResponse> parcelas;
}
