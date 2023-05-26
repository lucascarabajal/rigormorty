package com.disenio.rigormorty.models.responses;

import com.disenio.rigormorty.dto.ClienteRegistroDTO;
import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class RegistroCompraResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double entrega;
    private Integer totalCuotas;
    private Integer cuotasPagas;
    private Double totalPagar;
    private LocalDate fechaCompra;
    private LocalDate vencimiento;
    private LocalDate pago;
    private String formaPago;
    private ClienteAddResponse cliente;
    private List<ParcelaClienteResponse> parcelas;
}
