package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/user")
@RestController

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario(){
        return usuarioService.getUsuario();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return ResponseEntity.ok().body(this.usuarioService.updateUsuario(usuario));
    }

}