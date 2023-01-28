package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Domicilio;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DomicilioService {
    ResponseEntity<Domicilio> addDomicilio(Domicilio domicilio);
    ResponseEntity<List<Domicilio>> getDomicilios();
    Object updateDomicilio(Domicilio domicilio);
}
