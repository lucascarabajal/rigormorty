package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.EstadoParcela;
import com.disenio.rigormorty.service.EstadoParcelaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/estado_parcela")
@RestController
public class EstadoParcelaController {
    @Autowired
    private EstadoParcelaServiceImpl estadoParcelaServiceImpl;

    @PostMapping
    public ResponseEntity<EstadoParcela> addEstadoParcela(@RequestBody EstadoParcela estadoParcela){
        return estadoParcelaServiceImpl.addEstadoParcela(estadoParcela);
    }

    @GetMapping
    public ResponseEntity<List<EstadoParcela>> getEstadoParcelas(){
        return estadoParcelaServiceImpl.getEstadoParcelas;
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateEstadoParcela(@PathVariable Long id, @RequestBody EstadoParcela estadoParcela){
        estadoParcela.setId(id);
        return ResponseEntity.ok().body(this.estadoParcelaServiceImpl.updateEstadoParcela(estadoParcela));
    }
}
