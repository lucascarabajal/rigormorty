package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.models.request.UserRegisterRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    Usuario getUser(String username);
    Usuario createUser(UserRegisterRequestModel user);
    Integer countUsers();
    Usuario updateUser(UserRegisterRequestModel userRegisterRequestModel);
    ResponseEntity<Object> delete(Long id);
}
