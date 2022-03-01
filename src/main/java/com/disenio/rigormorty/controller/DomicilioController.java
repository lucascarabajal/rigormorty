package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.entity.Domicilio;
import com.disenio.rigormorty.service.DifuntoService;
import com.disenio.rigormorty.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/domicilio")
@RestController
public class DomicilioController {
    @Autowired
    private DomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<Domicilio> addDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.addDomicilio(domicilio);
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> getDomicilio(){
        return domicilioService.getDomicilio();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateDomicilio(@PathVariable Long id, @RequestBody Domicilio domicilio){
        domicilio.setId(id);
        return ResponseEntity.ok().body(this.domicilioService.updateDomicilio(domicilio));
    }
}
