package com.disenio.rigormorty.models.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegisterRequestModel {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max = 30)
    private String username;

    @NotBlank
    @Size(min=6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 9, max = 15)
    private Integer telefono;

}
