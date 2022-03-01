package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Cementerio;
import com.disenio.rigormorty.service.CementerioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cementerio")
@RestController
public class CementerioController {
    @Autowired
    private CementerioService cementerioService;

    @PostMapping
    public ResponseEntity<Cementerio> addCementerio(@RequestBody Cementerio cementerio){
        return cementerioService.addCementerio(cementerio);
    }

    @GetMapping
    public ResponseEntity<List<Cementerio>> getCementerios(){
        return cementerioService.getCementerios();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCementerio(@PathVariable Long id, @RequestBody Cementerio cementerio){
        cementerio.setId(id);
        return ResponseEntity.ok().body(this.cementerioService.updateCementerio(cementerio));
    }
}
