package com.disenio.rigormorty.models.request;

import com.disenio.rigormorty.entity.Domicilio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ClienteAddRequest extends PersonaRequest{

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=9, max = 14)
    private String telefono;

    private List<Domicilio> domicilios;
}
