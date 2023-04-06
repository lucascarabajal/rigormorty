package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.models.request.ClienteAddRequest;
import com.disenio.rigormorty.models.responses.ClienteAddResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {
    ResponseEntity<ClienteAddResponse> addCliente(ClienteAddRequest cliente);
    ResponseEntity<List<ClienteAddResponse>> getClientes();
    Object updateCliente(Cliente cliente);

    Cliente getById(Long id);
}
