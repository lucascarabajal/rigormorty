package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cuota;
import com.disenio.rigormorty.models.request.PagarCuotaRequest;
import org.springframework.http.ResponseEntity;

public interface CuotaService {
    ResponseEntity<Object> add(Cuota cuota);

    Cuota pagoCuota(PagarCuotaRequest cuotaRequest);
}
