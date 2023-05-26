package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import com.disenio.rigormorty.models.responses.RegistroStatsResponse;
import com.disenio.rigormorty.service.RegistroCompraService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/registrocompra")
@RestController
@AllArgsConstructor
public class RegistroCompraController {

    private final RegistroCompraService registroCompraService;

    @PostMapping
    public ResponseEntity<RegistroCompraResponse> addRegistroCompra(@RequestBody RegistroCompra registroCompra){
        return ResponseEntity.ok().body(registroCompraService.addRegistroCompra(registroCompra));
    }

    @GetMapping
    public ResponseEntity<List<RegistroCompraResponse>> getRegistroCompras(){
        return ResponseEntity.ok().body(registroCompraService.getRegistroCompras());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateRegistroCompra(@PathVariable Long id, @RequestBody RegistroCompra registroCompra) {
        registroCompra.setId(id);
        return ResponseEntity.ok().body(this.registroCompraService.updateRegistroCompra(registroCompra));
    }

    @GetMapping("{dni}")
    public ResponseEntity<Object> pagoCuota(@PathVariable Integer dni){
        return ResponseEntity.ok().body(this.registroCompraService.getRegistroCompraByCliente(dni));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RegistroStatsResponse>> getRegistrosByUser(@PathVariable Long id){
        return ResponseEntity.ok().body(this.registroCompraService.getRegistrosByUser(id));
    }
}
