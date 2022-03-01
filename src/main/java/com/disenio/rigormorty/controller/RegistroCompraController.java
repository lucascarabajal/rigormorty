package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.service.RegistroCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/registrocompra")
@RestController
public class RegistroCompraController {
    @Autowired
    private RegistroCompraService registroCompraService;

    @PostMapping
    public ResponseEntity<RegistroCompra> addRegistroCompra(@RequestBody RegistroCompra registroCompra){
        return registroCompraService.addRegistroCompra(registroCompra);
    }

    @GetMapping
    public ResponseEntity<List<RegistroCompra>> getRegistroCompra(){
        return  registroCompraService.getRegistroCompra();
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateRegistroCompra(@PathVariable Long id, @RequestBody RegistroCompra registroCompra) {
        registroCompra.setId(id);
        return ResponseEntity.ok().body(this.registroCompraService.updateRegistroCompra(registroCompra));
    }

}
