package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.models.responses.ParcelaAllResponse;
import com.disenio.rigormorty.models.responses.ParcelaClienteResponse;
import com.disenio.rigormorty.service.ParcelaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parcela")
@RestController
@AllArgsConstructor
public class ParcelaController {

    private final ParcelaService parcelaService;

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

    @DeleteMapping("desvincular/{id}")
    public ResponseEntity<Object> desvincular(@PathVariable Long id){
        return ResponseEntity.ok().body(this.parcelaService.desvincular(id));
    }
}
