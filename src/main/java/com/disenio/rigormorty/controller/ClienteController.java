package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.models.request.ClienteAddRequest;
import com.disenio.rigormorty.models.responses.ClienteAddResponse;
import com.disenio.rigormorty.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/cliente")
@RestController
public class ClienteController {
    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @PostMapping
    public ResponseEntity<ClienteAddResponse> addCliente(@Valid @RequestBody ClienteAddRequest cliente){
        return clienteServiceImpl.addCliente(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteAddResponse>>getClientes(){
        return clienteServiceImpl.getClientes();
    }

    //TODO update realizar un request
    @PutMapping("{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        return ResponseEntity.ok().body(this.clienteServiceImpl.updateCliente(cliente));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteAddResponse> getClienteById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.clienteServiceImpl.getById(id));
    }

}
