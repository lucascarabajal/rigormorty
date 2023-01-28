package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Zona;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ZonaService {
    ResponseEntity<Zona> addZona(Zona zona);
    ResponseEntity<List<Zona>> getZonas();
    Object updateZona(Zona zona);
}
