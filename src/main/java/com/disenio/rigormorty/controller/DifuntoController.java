package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.dto.DifuntoDTO;
import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.enums.NombreParcela;
import com.disenio.rigormorty.models.responses.DifuntoResponse;
import com.disenio.rigormorty.service.DifuntoService;
import com.disenio.rigormorty.service.DifuntoServiceImpl;
import com.disenio.rigormorty.service.ParcelaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/difunto")
@RestController
@AllArgsConstructor
public class DifuntoController {

    private final DifuntoService difuntoService;

    @PostMapping
    public ResponseEntity<Difunto> addDifunto(@RequestBody Difunto difunto){
        return difuntoService.addDifunto(difunto);
    }

    @GetMapping
    public ResponseEntity<List<DifuntoResponse>> getDifuntos(){
        return difuntoService.getDifuntos();
    }

    @PutMapping()
    public ResponseEntity<Object> updateDifunto(@RequestBody Difunto difunto){
        return ResponseEntity.ok().body(this.difuntoService.updateDifunto(difunto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(difuntoService.delete(id));
    }
}
