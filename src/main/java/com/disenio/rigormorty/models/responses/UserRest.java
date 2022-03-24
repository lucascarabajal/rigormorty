package com.disenio.rigormorty.models.responses;

import lombok.Data;

import java.util.Date;

@Data
public class UserRest {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Date fechaNac;
    private String email;
    private String username;
    private String telefono;
}
