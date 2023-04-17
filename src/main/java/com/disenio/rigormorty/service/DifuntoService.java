package com.disenio.rigormorty.service;

import com.disenio.rigormorty.dto.DifuntoDTO;
import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.models.responses.DifuntoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DifuntoService {
    ResponseEntity<Difunto> addDifunto(Difunto difunto);
    ResponseEntity<List<DifuntoResponse>> getDifuntos();
    Object updateDifunto(Difunto difunto);
}
