package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Difunto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DifuntoService {
    ResponseEntity<Difunto> addDifunto(Difunto difunto);
    ResponseEntity<List<Difunto>> getDifuntos();
    Object updateDifunto(Difunto difunto);
}
