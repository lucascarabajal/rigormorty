package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cementerio;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CementerioService {
    ResponseEntity<Cementerio> addCementerio(Cementerio cementerio);
    ResponseEntity<List<Cementerio>> getCementerios();
    Object updateCementerio(Cementerio cementerio);
}
