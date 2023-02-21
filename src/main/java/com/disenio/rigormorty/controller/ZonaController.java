package com.disenio.rigormorty.controller;

import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.exception.EqualObjectException;
import com.disenio.rigormorty.models.responses.ZonaResponse;
import com.disenio.rigormorty.service.ZonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
@RequestMapping("/zona")
@RestController
public class ZonaController {
    @Autowired
    private ZonaServiceImpl zonaServiceImpl;

    @PostMapping
    public ResponseEntity<Zona> addZona(@RequestBody Zona zona){
        return zonaServiceImpl.addZona(zona);
    }

    @GetMapping
    public ResponseEntity<List<Zona>> getZona(){
        return zonaServiceImpl.getZonas();
    }

    @GetMapping("{id}")
    public ZonaResponse getZonaByID(@PathVariable("id") Long id){
        return this.zonaServiceImpl.findById(id);
    }



    @PutMapping("{id}")
    public ResponseEntity<Object> updateZona(@RequestBody Zona zona) {
        return ResponseEntity.ok().body(this.zonaServiceImpl.updateZona(zona));
    }

    @PostMapping("/all")
    public ResponseEntity<Object> addListZona(@RequestBody List<Zona> zonas){
        this.zonaServiceImpl.addListZona(zonas);
        return ResponseEntity.ok().body("Zonas Creadas");
    }
}
