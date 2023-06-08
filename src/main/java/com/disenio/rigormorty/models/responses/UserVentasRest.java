package com.disenio.rigormorty.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class UserVentasRest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
}
