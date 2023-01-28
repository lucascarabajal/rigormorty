package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.FormaPago;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FormaPagoService {
    ResponseEntity<FormaPago> addFormaPago(FormaPago formaPago);
    ResponseEntity<List<FormaPago>> getFormaPagos();
    Object updateFormaPago(FormaPago formaPago);
}
