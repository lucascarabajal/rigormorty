package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.FormaPago;
import com.disenio.rigormorty.service.FormaPagoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/forma_pago")
@RestController
public class FormaPagoController {
    @Autowired
    private FormaPagoServiceImpl formaPagoServiceImpl;

    @PostMapping
    public ResponseEntity<FormaPago> addFormaPago(@RequestBody FormaPago formaPago){
        return formaPagoServiceImpl.addFormaPago(formaPago);
    }

    @GetMapping
    public ResponseEntity<List<FormaPago>> getFormaPagos(){
        return formaPagoServiceImpl.getFormaPagos();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateFormaPago(@PathVariable Long id, @RequestBody FormaPago formaPago){
        formaPago.setId(id);
        return ResponseEntity.ok().body(this.formaPagoServiceImpl.updateFormaPago(formaPago));
    }
}
