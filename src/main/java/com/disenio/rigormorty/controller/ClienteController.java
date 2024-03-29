package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.models.request.ClienteAddRequest;
import com.disenio.rigormorty.models.responses.ClienteAddResponse;
import com.disenio.rigormorty.service.ClienteService;
import com.disenio.rigormorty.service.ClienteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/cliente")
@RestController
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteAddResponse> addCliente(@Valid @RequestBody ClienteAddRequest cliente){
        return clienteService.addCliente(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteAddResponse>>getClientes(){
        return clienteService.getClientes();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        return ResponseEntity.ok().body(this.clienteService.updateCliente(cliente));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteAddResponse> getClienteById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.clienteService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.delete(id));
    }

    @GetMapping("/parcelas")
    public ResponseEntity<List<ClienteAddResponse>> clientesWithParcelas(){
        return ResponseEntity.ok().body(clienteService.clientesWithParcelas());
    }

}
