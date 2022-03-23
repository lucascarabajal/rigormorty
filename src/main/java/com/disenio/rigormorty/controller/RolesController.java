package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Roles;
import com.disenio.rigormorty.service.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/roles")
@RestController
public class RolesController {

    @Autowired
    private RolesServiceImpl rolesServiceImpl;

    @PostMapping
    public ResponseEntity<Roles> addRol(@RequestBody Roles roles){
        return rolesServiceImpl.addRol(roles);
    }

    @GetMapping
    public ResponseEntity<List<Roles>> getRoles(){
        return  rolesServiceImpl.getRoles();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateRoles(@PathVariable Long id, @RequestBody Roles roles) {
        roles.setId(id);
        return ResponseEntity.ok().body(this.rolesServiceImpl.updateRol(roles));
    }
}
