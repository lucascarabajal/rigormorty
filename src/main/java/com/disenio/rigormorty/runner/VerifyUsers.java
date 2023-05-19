package com.disenio.rigormorty.runner;

import com.disenio.rigormorty.entity.Roles;
import com.disenio.rigormorty.models.request.UserRegisterRequestModel;
import com.disenio.rigormorty.repository.RolesRepository;
import com.disenio.rigormorty.service.RolesService;
import com.disenio.rigormorty.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VerifyUsers implements ApplicationRunner {

    private final UsuarioService usuarioService;
    private final RolesService rolesService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        verifyRoles();
        verifyUsers();
    }

    public void verifyRoles(){
        if (rolesService.countRoles()==0){
            Roles rol = new Roles();
            rol.setNombre("ADMIN");
            rolesService.addRol(rol);
            Roles rol1 = new Roles();
            rol1.setNombre("VENDEDOR");
            rolesService.addRol(rol1);
        }
    }

    public void verifyUsers(){

        if (usuarioService.countUsers()==0){
            Roles rol = new Roles();
            rol.setId(1L);
            rol.setNombre("ADMIN");

            UserRegisterRequestModel usuario = new UserRegisterRequestModel();
            usuario.setRol(rol);
            usuario.setEmail("admin@admin.com");
            usuario.setNombre("admin");
            usuario.setApellido("admin");
            usuario.setDni(123456789);
            usuario.setTelefono("123456789");
            usuario.setUsername("administrador");
            usuario.setPassword("administrador");
            usuarioService.createUser(usuario);
        }
    }


}
