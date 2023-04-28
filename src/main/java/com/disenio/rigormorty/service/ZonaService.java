package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.models.responses.ZonaAllResponse;
import com.disenio.rigormorty.models.responses.ZonaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ZonaService {
    ResponseEntity<Zona> addZona(Zona zona);
    ResponseEntity<List<ZonaResponse>> getZonas();
    Object updateZona(Zona zona);
    void addListZona(List<Zona> zonas);
    void delete(Long id);
    ZonaAllResponse findById(Long id);
}
