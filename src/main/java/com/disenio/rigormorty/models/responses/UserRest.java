package com.disenio.rigormorty.models.responses;

import com.disenio.rigormorty.entity.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserRest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Date fechaNac;
    private String email;
    private String username;
    private String telefono;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Roles rol;
}
