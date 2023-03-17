package com.disenio.rigormorty.dto;

import com.disenio.rigormorty.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class RegistroDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double entrega;
    private Integer totalCuotas;
    private Integer cuotasPagas;
    private Double totalPagar;
    private Date vencimiento;
    private Date pago;
    private ClienteRegistroDTO clienteRegistroDTO;
    private ParcelaDTO parcelaDTO;
    private Usuario usuario;
}
