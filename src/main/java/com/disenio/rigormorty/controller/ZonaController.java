package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.service.ZonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/zona")
@RestController
public class ZonaController {
    @Autowired
    private ZonaServiceImpl zonaServiceImpl;

    @PostMapping
    public ResponseEntity<Zona> addZona(@RequestBody Zona zona) {
        return zonaServiceImpl.addZona(zona);
    }

    @GetMapping
    public ResponseEntity<List<Zona>> getZona(){
        return zonaServiceImpl.getZonas();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateZona(@RequestBody Zona zona) {
        return ResponseEntity.ok().body(this.zonaServiceImpl.updateZona(zona));
    }
}
