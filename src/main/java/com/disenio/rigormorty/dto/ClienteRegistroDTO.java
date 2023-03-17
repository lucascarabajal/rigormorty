package com.disenio.rigormorty.dto;

import com.disenio.rigormorty.entity.Domicilio;
import com.disenio.rigormorty.entity.Persona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ClienteRegistroDTO extends Persona implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String telefono;
    private List<Domicilio> domicilios;

}
