package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.Roles;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RolesServiceImpl implements RolesService{
    private final RolesRepository rolesRepository;

    @Override
    public ResponseEntity<Roles> addRol(Roles roles){
        Roles newRol = rolesRepository.save(roles);

        return ResponseEntity.ok(newRol);
    }

    @Override
    public Integer countRoles() {
        return rolesRepository.countAllByIdIsNotNull();
    }
}
