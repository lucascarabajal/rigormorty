package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.exception.EqualObjectException;
import com.disenio.rigormorty.models.responses.ZonaAllResponse;
import com.disenio.rigormorty.models.responses.ZonaResponse;
import com.disenio.rigormorty.service.ZonaService;
import com.disenio.rigormorty.service.ZonaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
@RequestMapping("/zona")
@RestController
@AllArgsConstructor
public class ZonaController {

    private ZonaService zonaService;

    @PostMapping
    public ResponseEntity<Zona> addZona(@RequestBody Zona zona){
        return zonaService.addZona(zona);
    }

    @GetMapping
    public ResponseEntity<List<ZonaResponse>> getZona(){
        return zonaService.getZonas();
    }

    @GetMapping("{id}")
    public ZonaAllResponse getZonaByID(@PathVariable("id") Long id){
        return this.zonaService.findById(id);
    }

    @GetMapping("nombre/{nombreZona}")
    public ZonaResponse getZonaByName(@PathVariable String nombreZona){
        return this.zonaService.findByName(nombreZona);
    }

    @PutMapping
    public ResponseEntity<Object> updateZona(@RequestBody Zona zona) {
        return ResponseEntity.ok().body(this.zonaService.updateZona(zona));
    }

    @PostMapping("/all")
    public ResponseEntity<Object> addListZona(@RequestBody List<Zona> zonas){
        this.zonaService.addListZona(zonas);
        return ResponseEntity.ok().body("Zonas Creadas");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteZona(@PathVariable("id")Long id){
        zonaService.delete(id);
        return ResponseEntity.accepted().body("Se eliminó la zona correctamente");
    }
}
