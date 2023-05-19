package com.disenio.rigormorty.service;



import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.Roles;
import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.enums.NombreRol;
import com.disenio.rigormorty.exception.CustomException;
import com.disenio.rigormorty.exception.EqualObjectException;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.request.UserRegisterRequestModel;
import com.disenio.rigormorty.models.responses.UserRest;
import com.disenio.rigormorty.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper mapper;

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

        if (Objects.nonNull(usuarioRepository.findByUsername(user.getUsername()))) throw new EqualObjectException("Ya existe usuario con ese Username");

        Usuario usuario = new Usuario();
        NombreRol.valueOf(user.getRol().getNombre());
        BeanUtils.copyProperties(user,usuario);
        usuario.setActivo(true);
        usuario.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return usuarioRepository.save(usuario);
    }

    public Usuario updateUser(UserRegisterRequestModel user){
        Optional<Usuario> usuarioToUpdate = Optional.ofNullable(usuarioRepository.findByUsername(user.getUsername()));

        if(usuarioToUpdate.isPresent()){
            Usuario usuario = usuarioToUpdate.get();

            usuario.setUsername(user.getUsername());
            usuario.setRol(user.getRol());
            usuario.setNombre(user.getNombre());
            usuario.setApellido(user.getApellido());
            usuario.setEmail(user.getEmail());
            usuario.setTelefono(user.getTelefono());
            usuario.setDni(user.getDni());
            usuario.setRol(user.getRol());
            usuario.setActivo(user.isActivo());

            usuarioRepository.save(usuario);

            return usuario;
        }else {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public Integer countUsers() {
        return usuarioRepository.countAllByUsernameNotNull();
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        Usuario usuario = usuarioRepository.getById(id);
        if (usuarioRepository.getAdmins().size()==1 && usuario.getRol().getNombre().equals("ADMIN")) throw new CustomException("No puede eliminar este administrador ya que no existe otro");
        usuario.setActivo(false);
        updateUser(this.mapper.map(usuario, UserRegisterRequestModel.class));
        return ResponseEntity.ok().body("Se borró correctamente el usuario");
    }

    @Override
    public List<UserRest> getAll() {
        return usuarioRepository.getAllByActivoIsTrue().stream().map(usuario -> this.mapper.map(usuario,UserRest.class)).collect(Collectors.toList());
    }
}
