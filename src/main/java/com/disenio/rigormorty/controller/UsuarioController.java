package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.exception.EqualObjectException;
import com.disenio.rigormorty.models.request.UserPassRequest;
import com.disenio.rigormorty.models.request.UserRegisterRequestModel;
import com.disenio.rigormorty.models.responses.UserRest;
import com.disenio.rigormorty.service.UsuarioService;
import com.disenio.rigormorty.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.LuhnCheck;
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

    @PutMapping
    public ResponseEntity<UserRest> updatePersonalUser(@RequestBody UserRegisterRequestModel userModel){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updatePersonalUser(userModel));
    }

    @PutMapping("/rol")
    public ResponseEntity<UserRest> updateRol(@RequestBody UserRegisterRequestModel user){
        return ResponseEntity.ok().body(usuarioService.updateRol(user));
    }

    @PutMapping("/password")
    public ResponseEntity<UserRest> updatePassword(@RequestBody UserPassRequest user){
        return ResponseEntity.ok().body(usuarioService.updatePassword(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.delete(id));
    }

    @GetMapping("all")
    public ResponseEntity<List<UserRest>> getAll(){
        return ResponseEntity.ok().body(usuarioService.getAll());
    }
}