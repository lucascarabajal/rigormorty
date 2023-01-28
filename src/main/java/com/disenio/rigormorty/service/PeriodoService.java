package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Periodo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PeriodoService {
    ResponseEntity<Periodo> addPeriodo(Periodo periodo);
    ResponseEntity<List<Periodo>> getPeriodos();
    Object updatePeriodo(Periodo periodo);
}
