package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.models.request.RegistroCompraRequest;
import com.disenio.rigormorty.models.responses.RegistroCompraResponse;
import com.disenio.rigormorty.models.responses.RegistroStatsResponse;
import com.disenio.rigormorty.models.responses.VentasResponses;

import java.util.List;

public interface RegistroCompraService {
    RegistroCompraResponse addRegistroCompra(RegistroCompraRequest registroCompra);
    List<RegistroCompraResponse> getRegistroCompras();
    RegistroCompra getById(Long id);
    Object updateRegistroCompra(RegistroCompra registroCompra);
    List<RegistroCompraResponse> getRegistroCompraByCliente(Integer dni);
    List<RegistroStatsResponse> getRegistrosByUser(Long id);

    List<VentasResponses> getVentas();
}
