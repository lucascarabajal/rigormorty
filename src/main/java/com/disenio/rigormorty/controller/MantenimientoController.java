package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.service.MantenimientoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mantenimiento")
@RestController
public class MantenimientoController {
    @Autowired
    private MantenimientoServiceImpl mantenimientoServiceImpl;

    @PostMapping
    public ResponseEntity<Mantenimiento> addMantenimiento(@RequestBody Mantenimiento mantenimiento){
        return mantenimientoServiceImpl.addMantenimiento(mantenimiento);
    }

    @GetMapping
    public ResponseEntity<List<Mantenimiento>>getMantenimiento(){
        return mantenimientoServiceImpl.getMantenimientos();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateMantenimiento(@PathVariable Long id, @RequestBody Mantenimiento mantenimiento) {
        mantenimiento.setId(id);
        return ResponseEntity.ok().body(this.mantenimientoServiceImpl.updateMantenimiento(mantenimiento));
    }
}
