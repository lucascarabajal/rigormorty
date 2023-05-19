package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import com.disenio.rigormorty.models.responses.RegistroStatsResponse;

import java.util.List;

public interface RegistroCompraService {
    RegistroCompraResponse addRegistroCompra(RegistroCompra registroCompra);
    List<RegistroCompraResponse> getRegistroCompras();
    Object updateRegistroCompra(RegistroCompra registroCompra);
    RegistroCompraResponse pagoCuota(Long id, Integer cantidad);
    List<RegistroCompraResponse> getRegistroCompraByCliente(Integer dni);
    List<RegistroStatsResponse> getRegistrosByUser(Long id);
}
