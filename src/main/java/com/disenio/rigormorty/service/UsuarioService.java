package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Periodo;
import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<Usuario> addUsuario(Usuario usuario){
        Usuario newUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok(newUsuario);
    }

    public List<Usuario> getUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios;
    }

    public Object updateUsuario(Usuario usuario){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuario.getId());
        if(optionalUsuario.isPresent()){
            Usuario usuarioToUpdate = optionalUsuario.get();

            usuarioToUpdate.setUsername(usuario.getUsername());
            usuarioToUpdate.setPassword(usuario.getPassword());
            usuarioToUpdate.setDomicilios(usuario.getDomicilios());
            usuarioToUpdate.setEmail(usuario.getEmail());
            usuarioToUpdate.setRoles(usuario.getRoles());
            usuarioToUpdate.setTelefono(usuario.getTelefono());
            usuarioToUpdate.setNombre(usuario.getNombre());
            usuarioToUpdate.setApellido(usuario.getApellido());
            usuarioToUpdate.setDni(usuario.getDni());
            usuarioToUpdate.setFechaNac(usuario.getFechaNac());


            usuarioRepository.save(usuarioToUpdate);

            return usuarioToUpdate;
        }else{
            throw new ResourceNotFoundException("Usuario no encontrado");
        }


    }
}
