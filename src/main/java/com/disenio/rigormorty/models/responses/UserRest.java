package com.disenio.rigormorty.models.responses;

import lombok.Data;

@Data
public class UserRest {
    private Long id;
    private String email;
    private String username;
    private Integer telefono;
}
