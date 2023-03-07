package com.disenio.rigormorty.jobs;

import com.disenio.rigormorty.entity.Roles;
import com.disenio.rigormorty.models.request.UserRegisterRequestModel;
import com.disenio.rigormorty.service.RolesService;
import com.disenio.rigormorty.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyUsers {

    private final UsuarioService usuarioService;
    private final RolesService rolesService;

    @Scheduled(fixedRate = 604000000)
    public void verifyRoles(){
        if (rolesService.countRoles()==0){
            Roles rol = new Roles();
            rol.setId(1L);
            rol.setNombre("ADMIN");

            rolesService.addRol(rol);
        }
    }

    @Scheduled(fixedRate = 604000000)
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
            usuario.setUsername("admin");
            usuario.setPassword("admin");
            usuarioService.createUser(usuario);
        }
    }
}
