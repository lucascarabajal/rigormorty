package com.disenio.rigormorty.models.request;

import com.disenio.rigormorty.entity.Roles;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Data
public class UserRegisterRequestModel {

    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=5, max = 30)
    private String username;

    @NotBlank
    @Size(min=5, max = 40)
    private String password;

    @NotBlank
    @Size(min = 9, max = 15)
    private String telefono;

    @NotBlank
    @Size(max = 25)
    private String nombre;

    @NotBlank
    @Size(max = 25)
    private String apellido;

    private LocalDate fechaNac;

    private Roles rol;

    private boolean activo;

    @Min(value = 100000, message = "The value must be positive")
    private Integer dni;
}
