package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Cuota;
import com.disenio.rigormorty.models.request.PagarCuotaRequest;
import com.disenio.rigormorty.models.responses.CuotaAllResponse;
import com.disenio.rigormorty.models.responses.CuotaResponse;
import com.disenio.rigormorty.service.CuotaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cuota")
@RestController
@AllArgsConstructor
public class CuotaController {
    private final CuotaService cuotaService;

    @PostMapping
    public ResponseEntity<Cuota> pagar(@RequestBody PagarCuotaRequest pagarCuotaRequest){
        return ResponseEntity.ok().body(cuotaService.pagoCuota(pagarCuotaRequest));
    }

    @GetMapping
    public ResponseEntity<List<CuotaAllResponse>> getAll(){
        return ResponseEntity.ok().body(cuotaService.getCuotas());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<List<CuotaAllResponse>> getAllByCliente(@PathVariable Integer dni){
        return ResponseEntity.ok().body(cuotaService.getCuotasByCliente(dni));
    }

}
