package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.exception.EqualObjectException;
import com.disenio.rigormorty.models.request.UserRegisterRequestModel;
import com.disenio.rigormorty.models.responses.UserRest;
import com.disenio.rigormorty.service.UsuarioService;
import com.disenio.rigormorty.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UserRest> createUser(@RequestBody @Valid UserRegisterRequestModel usuarioModel){
        Usuario user = usuarioService.createUser(usuarioModel);
        UserRest userRest = new UserRest();

        BeanUtils.copyProperties(user,userRest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userRest);
    }

    @GetMapping
    public UserRest getUser(Authentication authentication){
        String username = authentication.getPrincipal().toString();
        Usuario usuario = usuarioService.getUser(username);

        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(usuario,userRest);

        return userRest;
    }

}