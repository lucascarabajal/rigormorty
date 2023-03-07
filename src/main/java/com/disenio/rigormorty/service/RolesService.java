package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Roles;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolesService {
    ResponseEntity<Roles> addRol(Roles roles);
    ResponseEntity<List<Roles>> getRoles();
    Object updateRol(Roles roles);
    Integer countRoles();
}
