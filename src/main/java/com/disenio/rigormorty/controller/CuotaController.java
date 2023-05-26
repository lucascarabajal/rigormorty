package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Cuota;
import com.disenio.rigormorty.models.request.PagarCuotaRequest;
import com.disenio.rigormorty.service.CuotaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cuota")
@RestController
@AllArgsConstructor
public class CuotaController {
    private final CuotaService cuotaService;

    @PostMapping
    public ResponseEntity<Cuota> pagar(@RequestBody PagarCuotaRequest pagarCuotaRequest){
        return ResponseEntity.ok().body(cuotaService.pagoCuota(pagarCuotaRequest));
    }

}
