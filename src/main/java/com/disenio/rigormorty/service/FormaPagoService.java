package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.FormaPago;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.FormaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagoService {

    private final FormaPagoRepository formaPagoRepository;

    @Autowired
    public FormaPagoService(FormaPagoRepository formaPagoRepository) {
        this.formaPagoRepository = formaPagoRepository;
    }

    public ResponseEntity<FormaPago> addFormaPago(FormaPago formaPago){
        FormaPago newFormaPago = formaPagoRepository.save(formaPago);

        return ResponseEntity.ok(newFormaPago);
    }

    public List<FormaPago> getFormaPago(){
        List<FormaPago> formasPago = formaPagoRepository.findAll();

        return formasPago;
    }

    public Object updateFormaPago(FormaPago formaPago){
        Optional<FormaPago> optionalFormaPago = formaPagoRepository.findById(formaPago.getId());
        if(optionalFormaPago.isPresent()){
            FormaPago formaPagoToUpdate = optionalFormaPago.get();

            formaPagoToUpdate.setFormaPago(formaPago.getFormaPago());
            formaPagoToUpdate.setRegistro(formaPago.getRegistro());

            formaPagoRepository.save(formaPagoToUpdate);

            return formaPagoToUpdate;
        }else{
            throw new ResourceNotFoundException("Forma de pago no encontrado");
        }


    }

}
