package com.disenio.rigormorty.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ClienteResponse extends PersonaResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String telefono;
}
