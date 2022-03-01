package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.service.DifuntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/difunto")
@RestController
public class DifuntoController {
    @Autowired
    private DifuntoService difuntoService;

    @PostMapping
    public ResponseEntity<Difunto> addDifunto(@RequestBody Difunto difunto){
        return difuntoService.addDifunto(difunto);
    }

    @GetMapping
    public ResponseEntity<List<Difunto>> getDifunto(){
        return difuntoService.getDifunto();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateDifunto(@PathVariable Long id, @RequestBody Difunto difunto){
        difunto.setId(id);
        return ResponseEntity.ok().body(this.difuntoService.updateDifuntos(difunto));
    }
}
