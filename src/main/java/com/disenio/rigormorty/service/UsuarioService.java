package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.models.request.UserRegisterRequestModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    Usuario getUser(String username);
    Usuario createUser(UserRegisterRequestModel user);
}
