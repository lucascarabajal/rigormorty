package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.Roles;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService{
    private final RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public ResponseEntity<Roles> addRol(Roles roles){
        Roles newRol = rolesRepository.save(roles);

        return ResponseEntity.ok(newRol);
    }

    @Override
    public ResponseEntity<List<Roles>> getRoles(){
        List<Roles> roles = rolesRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    @Override
    public Object updateRol(Roles roles){
        Optional<Roles> optionalRoles = rolesRepository.findById(roles.getId());
        if(optionalRoles.isPresent()){
            Roles rolToUpdate = optionalRoles.get();

            rolToUpdate.setNombre(roles.getNombre());
            rolToUpdate.setUsuario(roles.getUsuario());

            rolesRepository.save(rolToUpdate);

            return rolToUpdate;
        }else{
            throw new ResourceNotFoundException("Rol no encontrado");
        }


    }
}
