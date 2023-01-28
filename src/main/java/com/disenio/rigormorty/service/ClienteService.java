package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {
    ResponseEntity<Cliente> addCliente(Cliente cliente);
    ResponseEntity<List<Cliente>> getClientes();
    Object updateCliente(Cliente cliente);
}
