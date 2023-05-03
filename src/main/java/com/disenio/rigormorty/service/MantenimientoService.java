package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Mantenimiento;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MantenimientoService {
    ResponseEntity<Object> addMantenimiento(Mantenimiento mantenimiento);
    ResponseEntity<List<Mantenimiento>> getMantenimientos();
    Object updateMantenimiento(Mantenimiento mantenimiento);
}
