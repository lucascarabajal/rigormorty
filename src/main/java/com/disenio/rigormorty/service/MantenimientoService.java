package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.models.responses.MantenimientoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MantenimientoService {
    ResponseEntity<Object> addMantenimiento(Mantenimiento mantenimiento);
    ResponseEntity<List<MantenimientoResponse>> getMantenimientos();
    Object updateMantenimiento(Mantenimiento mantenimiento);
}
