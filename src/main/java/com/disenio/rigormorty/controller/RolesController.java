package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Roles;
import com.disenio.rigormorty.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/roles")
@RestController
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @PostMapping
    public ResponseEntity<Roles> addRol(@RequestBody Roles roles){
        return rolesService.addRol(roles);
    }

    @GetMapping
    public ResponseEntity<List<Roles>> getRoles(){
        return  rolesService.getRoles();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateRoles(@PathVariable Long id, @RequestBody Roles roles) {
        roles.setId(id);
        return ResponseEntity.ok().body(this.rolesService.updateRol(roles));
    }
}
