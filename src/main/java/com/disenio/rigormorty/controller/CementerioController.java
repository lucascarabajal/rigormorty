package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Cementerio;
import com.disenio.rigormorty.service.CementerioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cementerio")
@RestController
public class CementerioController {
    @Autowired
    private CementerioServiceImpl cementerioServiceImpl;

    @PostMapping
    public ResponseEntity<Cementerio> addCementerio(@RequestBody Cementerio cementerio){
        return cementerioServiceImpl.addCementerio(cementerio);
    }

    @GetMapping
    public ResponseEntity<List<Cementerio>> getCementerios(){
        return cementerioServiceImpl.getCementerios();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCementerio(@PathVariable Long id, @RequestBody Cementerio cementerio){
        cementerio.setId(id);
        return ResponseEntity.ok().body(this.cementerioServiceImpl.updateCementerio(cementerio));
    }
}
