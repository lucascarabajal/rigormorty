package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.models.responses.MantenimientoResponse;
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
        return ResponseEntity.ok().body(mantenimientoService.addMantenimiento(mantenimiento));
    }

    @GetMapping
    public ResponseEntity<List<MantenimientoResponse>>getMantenimiento(){
        return mantenimientoService.getMantenimientos();
    }

    @GetMapping("{id}")
    public ResponseEntity<List<MantenimientoResponse>> getMantenimientoByCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(mantenimientoService.getMantenimientoByCliente(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateMantenimiento(@PathVariable Long id, @RequestBody Mantenimiento mantenimiento) {
        mantenimiento.setId(id);
        return ResponseEntity.ok().body(this.mantenimientoService.updateMantenimiento(mantenimiento));
    }
}
