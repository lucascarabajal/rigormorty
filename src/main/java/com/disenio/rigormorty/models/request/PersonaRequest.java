package com.disenio.rigormorty.models.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class PersonaRequest {

    @Size(min = 3)
    private String nombre;

    @Size(min = 3)
    private String apellido;

    @NotNull
    private Integer dni;

    @NotNull
    private LocalDate fechaNac;
}
