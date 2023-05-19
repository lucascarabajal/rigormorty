package com.disenio.rigormorty.dto;

import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.entity.EstadoParcela;
import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.entity.RegistroCompra;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ParcelaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer nivelActual;
    private String numeroParcela;
    private List<DifuntoDTO> difuntos = new ArrayList<>();
    private List<EstadoParcela> estados = new ArrayList<>();
    private Boolean asignada;
    private ClienteRegistroDTO cliente;
}
