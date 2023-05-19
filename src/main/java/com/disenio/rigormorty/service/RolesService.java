package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Roles;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolesService {
    ResponseEntity<Roles> addRol(Roles roles);
    Integer countRoles();
}
