package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.service.MantenimientoService;
import com.disenio.rigormorty.service.MantenimientoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mantenimiento")
@RestController
@AllArgsConstructor
public class MantenimientoController {
    
    private final MantenimientoService mantenimientoService;

    @PostMapping
    public ResponseEntity<Object> addMantenimiento(@RequestBody Mantenimiento mantenimiento){
        mantenimientoService.addMantenimiento(mantenimiento);
        return ResponseEntity.ok().body("Se creo correctamnete");
    }

    @GetMapping
    public ResponseEntity<List<Mantenimiento>>getMantenimiento(){
        return mantenimientoService.getMantenimientos();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateMantenimiento(@PathVariable Long id, @RequestBody Mantenimiento mantenimiento) {
        mantenimiento.setId(id);
        return ResponseEntity.ok().body(this.mantenimientoService.updateMantenimiento(mantenimiento));
    }
}
