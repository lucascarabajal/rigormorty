package com.disenio.rigormorty.models.request;

import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class UserLoginRequestModel {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
