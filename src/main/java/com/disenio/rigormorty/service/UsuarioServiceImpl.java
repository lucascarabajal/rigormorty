package com.disenio.rigormorty.service;



import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.models.request.UserRegisterRequestModel;
import com.disenio.rigormorty.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null){
            throw new UsernameNotFoundException(username);
        }

        return new User(usuario.getUsername(),usuario.getPassword(), new ArrayList<>());
    }

    @Override
    public Usuario getUser(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario createUser(UserRegisterRequestModel user) {
        Usuario usuario = new Usuario();

        BeanUtils.copyProperties(user,usuario);

        usuario.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return usuarioRepository.save(usuario);
    }
}
