package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ZonaController {
    @Autowired
    private ZonaService zonaService;

    @PostMapping
    public ResponseEntity<Zona> addZona(@RequestBody Zona zona) {
        return zonaService.addZona(zona);
    }

    @GetMapping
    public ResponseEntity<List<Zona>> getZona(){
        return zonaService.getZona();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateZona(@PathVariable String nombreZona, @RequestBody Zona zona) {
        zona.setNombreZona(nombreZona);
        return ResponseEntity.ok().body(this.zonaService.updateZona(zona));
    }
}
