package com.disenio.rigormorty.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class DifuntoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private LocalDate fechaNac;
    private LocalDateTime fechaDef;
    private String numExpediente;
    private Integer numNivel;
    private String acta;

}
