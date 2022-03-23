package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cementerio;
import com.disenio.rigormorty.entity.Mantenimiento;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MantenimientoService {
    ResponseEntity<Mantenimiento> addMantenimiento(Mantenimiento mantenimiento);
    ResponseEntity<List<Mantenimiento>> getMantenimientos();
    Object updateMantenimiento(Mantenimiento mantenimiento);
}
