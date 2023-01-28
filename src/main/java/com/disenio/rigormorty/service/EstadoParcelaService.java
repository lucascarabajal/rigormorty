package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.EstadoParcela;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstadoParcelaService {
    ResponseEntity<EstadoParcela> addEstadoParcela(EstadoParcela estadoParcela);
    ResponseEntity<List<EstadoParcela>> getEstadoParcelas();
    Object updateEstadoParcela(EstadoParcela estadoParcela);
}
