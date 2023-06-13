package com.disenio.rigormorty.models.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class UserPassRequest  {
    @NotBlank
    private String username;
    @NotBlank
    private String oldPass;
    @NotBlank
    private String newPass;
}
