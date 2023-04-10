package com.disenio.rigormorty.service;

import com.disenio.rigormorty.dto.RegistroDTO;
import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegistroCompraService {
    ResponseEntity<RegistroDTO> addRegistroCompra(RegistroCompra registroCompra);
    List<RegistroCompraResponse> getRegistroCompras();
    Object updateRegistroCompra(RegistroCompra registroCompra);

    RegistroCompraResponse pagoCuota(Long id, Integer cantidad);
}
