package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cliente")
@RestController
public class ClienteController {
    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @PostMapping
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente){
        return clienteServiceImpl.addCliente(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>>getClientes(){
        return clienteServiceImpl.getClientes();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        return ResponseEntity.ok().body(this.clienteServiceImpl.updateCliente(cliente));
    }

}
