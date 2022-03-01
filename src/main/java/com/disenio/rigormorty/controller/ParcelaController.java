package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.service.ParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parcela")
@RestController
public class ParcelaController {
    @Autowired
    private ParcelaService parcelaService;

    @PostMapping
    public ResponseEntity<Parcela> addParcela(@RequestBody Parcela parcela){
        return parcelaService.addParcela(parcela);
    }

    @GetMapping
    public ResponseEntity<List<Parcela>> getParcelas(){
        return parcelaService.getParcela();
    }

//    @GetMapping
//    public ResponseEntity<Parcela> getParcelaById(@PathVariable("id") Long id){
//        return parcelaService.getParcelaById()
//    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Parcela parcela) {
        parcela.setId(id);
        return ResponseEntity.ok().body(this.parcelaService.updateParcela(parcela));
    }
}
