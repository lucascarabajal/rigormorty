package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Periodo;
import com.disenio.rigormorty.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/periodo")
@RestController
public class PeriodoController {
    @Autowired
    private PeriodoService periodoService;

    @PostMapping
    public ResponseEntity<Periodo> addPeriodo(@RequestBody Periodo periodo){
        return periodoService.addPeriodo(periodo);
    }

    @GetMapping
    public ResponseEntity<List<Periodo>> getPeriodos(){
        return  periodoService.getPeriodos();
    }

//    @GetMapping
//    public ResponseEntity<Parcela> getParcelaById(@PathVariable("id") Long id){
//        return parcelaService.getParcelaById()
//    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePeriodo(@PathVariable Long id, @RequestBody Periodo periodo) {
        periodo.setId(id);
        return ResponseEntity.ok().body(this.periodoService.updatePeriodo(periodo));
    }

}
