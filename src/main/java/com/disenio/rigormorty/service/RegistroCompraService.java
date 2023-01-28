package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.RegistroCompra;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegistroCompraService {
    ResponseEntity<RegistroCompra> addRegistroCompra(RegistroCompra registroCompra);
    ResponseEntity<List<RegistroCompra>> getRegistroCompras();
    Object updateRegistroCompra(RegistroCompra registroCompra);
}
