package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.EstadoParcela;
import com.disenio.rigormorty.service.EstadoParcelaService;
import com.disenio.rigormorty.service.EstadoParcelaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/estado_parcela")
@RestController
public class EstadoParcelaController {
    @Autowired
    private EstadoParcelaService estadoParcelaService;

    @PostMapping
    public ResponseEntity<EstadoParcela> addEstadoParcela(@RequestBody EstadoParcela estadoParcela){
        return estadoParcelaService.addEstadoParcela(estadoParcela);
    }

    @GetMapping
    public ResponseEntity<List<EstadoParcela>> getEstadoParcelas(){
        return estadoParcelaService.getEstadoParcelas();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateEstadoParcela(@PathVariable Long id, @RequestBody EstadoParcela estadoParcela){
        estadoParcela.setId(id);
        return ResponseEntity.ok().body(this.estadoParcelaService.updateEstadoParcela(estadoParcela));
    }
}
