package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.models.responses.ParcelaAllResponse;
import com.disenio.rigormorty.models.responses.ParcelaClienteResponse;
import com.disenio.rigormorty.service.ParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parcela")
@RestController
public class ParcelaController {
    @Autowired
    private ParcelaService parcelaService;

    @PostMapping
    public ResponseEntity<Parcela> addParcela(@RequestBody Parcela parcela){
        return parcelaService.addParcela(parcela);
    }

    @GetMapping
    public ResponseEntity<List<ParcelaAllResponse>>getParcelas(){
        return parcelaService.getParcelas();
    }

    @GetMapping("{id}")
    public ParcelaDTO getParcelaById(@PathVariable("id") Long id){
        return parcelaService.getById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Parcela parcela) {
        parcela.setId(id);
        return ResponseEntity.ok().body(this.parcelaService.updateParcela(parcela));
    }

    @GetMapping("cliente/{id}")
    public List<ParcelaClienteResponse> getParcelasByCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(this.parcelaService.getParcelasByCliente(id)).getBody();
    }

    @GetMapping("libres/{id}")
    public List<ParcelaClienteResponse> getParcelasLibres(@PathVariable Long id){
        return ResponseEntity.ok().body(this.parcelaService.getParcelasDesocupadas(id)).getBody();
    }

    @GetMapping("desvincular/{id}")
    public ResponseEntity<Object> desvincular(@PathVariable Long id){
        this.parcelaService.desvincular(id);
        return ResponseEntity.ok().body("Se desvinculo correctamente la parcela");
    }
}
