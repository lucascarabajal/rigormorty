package com.disenio.rigormorty.service;

import com.disenio.rigormorty.dto.ParcelaDTO;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.models.responses.ParcelaClienteResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParcelaService {
    ResponseEntity<Parcela> addParcela(Parcela parcela);
    ResponseEntity<List<Parcela>> getParcelas();
    Object updateParcela(Parcela parcela);
    Object updateParcelaRegistro(ParcelaDTO parcela);
    ParcelaDTO getById(Long id);
    List<ParcelaClienteResponse>  getParcelasByCliente(Long idCliente);
}
