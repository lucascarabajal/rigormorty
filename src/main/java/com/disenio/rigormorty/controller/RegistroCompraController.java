package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.service.RegistroCompraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/registrocompra")
@RestController
public class RegistroCompraController {
    @Autowired
    private RegistroCompraServiceImpl registroCompraServiceImpl;

    @PostMapping
    public ResponseEntity<RegistroCompra> addRegistroCompra(@RequestBody RegistroCompra registroCompra){
        return registroCompraServiceImpl.addRegistroCompra(registroCompra);
    }

    @GetMapping
    public ResponseEntity<List<RegistroCompra>> getRegistroCompras(){
        return  registroCompraServiceImpl.getRegistroCompras();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateRegistroCompra(@PathVariable Long id, @RequestBody RegistroCompra registroCompra) {
        registroCompra.setId(id);
        return ResponseEntity.ok().body(this.registroCompraServiceImpl.updateRegistroCompra(registroCompra));
    }

}
