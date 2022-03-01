package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.entity.FormaPago;
import com.disenio.rigormorty.service.DifuntoService;
import com.disenio.rigormorty.service.FormaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/forma_pago")
@RestController
public class FormaPagoController {
    @Autowired
    private FormaPagoService formaPagoService;

    @PostMapping
    public ResponseEntity<FormaPago> addFormaPago(@RequestBody FormaPago formaPago){
        return formaPagoService.addFormaPago(formaPago);
    }

    @GetMapping
    public ResponseEntity<List<FormaPago>> getFormaPago(){
        return formaPagoService.getFormaPago();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateFormaPago(@PathVariable Long id, @RequestBody FormaPago formaPago){
        formaPago.setId(id);
        return ResponseEntity.ok().body(this.formaPagoService.updateFormaPago(formaPago));
    }
}
