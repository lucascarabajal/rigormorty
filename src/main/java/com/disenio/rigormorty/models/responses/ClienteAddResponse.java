package com.disenio.rigormorty.models.responses;

import com.disenio.rigormorty.entity.Domicilio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ClienteAddResponse extends PersonaResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String telefono;
    private List<Domicilio> domicilios;
}
