package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.dto.RegistroDTO;
import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
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
    public ResponseEntity<RegistroDTO> addRegistroCompra(@RequestBody RegistroCompra registroCompra){
        return registroCompraServiceImpl.addRegistroCompra(registroCompra);
    }

    @GetMapping
    public ResponseEntity<List<RegistroCompraResponse>> getRegistroCompras(){
        return ResponseEntity.ok().body(registroCompraServiceImpl.getRegistroCompras());
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateRegistroCompra(@PathVariable Long id, @RequestBody RegistroCompra registroCompra) {
        registroCompra.setId(id);
        return ResponseEntity.ok().body(this.registroCompraServiceImpl.updateRegistroCompra(registroCompra));
    }

    @GetMapping("{id}&{cantidad}")
    public ResponseEntity<Object> pagoCuota(@PathVariable Long id,@PathVariable Integer cantidad){
        return ResponseEntity.ok().body(this.registroCompraServiceImpl.pagoCuota(id, cantidad));
    }

}
