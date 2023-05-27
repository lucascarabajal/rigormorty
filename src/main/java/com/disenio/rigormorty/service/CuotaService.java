package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cuota;
import com.disenio.rigormorty.models.request.PagarCuotaRequest;
import com.disenio.rigormorty.models.responses.CuotaAllResponse;
import com.disenio.rigormorty.models.responses.CuotaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CuotaService {
    ResponseEntity<Object> add(Cuota cuota);
    Cuota pagoCuota(PagarCuotaRequest cuotaRequest);
    List<CuotaAllResponse> getCuotasByCliente(Integer dniCliente);
    List<CuotaAllResponse> getCuotas();
}
