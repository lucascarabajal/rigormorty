package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Parcela;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParcelaService {
    ResponseEntity<Parcela> addParcela(Parcela parcela);
    ResponseEntity<List<Parcela>> getParcelas();
    Object updateParcela(Parcela parcela);
}
