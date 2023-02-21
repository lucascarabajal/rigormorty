package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.dto.DifuntoDTO;
import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.service.DifuntoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/difunto")
@RestController
public class DifuntoController {
    @Autowired
    private DifuntoServiceImpl difuntoServiceImpl;

    @PostMapping
    public ResponseEntity<Difunto> addDifunto(@RequestBody Difunto difunto){
        return difuntoServiceImpl.addDifunto(difunto);
    }

    @GetMapping
    public ResponseEntity<List<DifuntoDTO>> getDifuntos(){
        return difuntoServiceImpl.getDifuntos();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateDifunto(@PathVariable Long id, @RequestBody Difunto difunto){
        difunto.setId(id);
        return ResponseEntity.ok().body(this.difuntoServiceImpl.updateDifunto(difunto));
    }
}
