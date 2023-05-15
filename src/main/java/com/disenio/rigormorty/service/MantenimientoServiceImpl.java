package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.enums.NombrePago;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.MantenimientoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
public class MantenimientoServiceImpl implements MantenimientoService {
    private final MantenimientoRepository mantenimientoRepository;

    @Override
    public ResponseEntity<Object> addMantenimiento(Mantenimiento mantenimiento){
        int periodo = obtenerPeriodo(mantenimiento.getPeriodo());

        mantenimiento.setFechaPago(Date.from(Instant.now()));
        mantenimiento.setFechaVencimiento(obtenerVencimiento(periodo));
        mantenimientoRepository.save(mantenimiento);

        return ResponseEntity.ok("Se pago correctamente");
    }

    @Override
    public ResponseEntity<List<Mantenimiento>> getMantenimientos(){
        List<Mantenimiento> mantenimientos = mantenimientoRepository.findAll();
        return ResponseEntity.ok(mantenimientos);
    }

    @Override
    public Object updateMantenimiento(Mantenimiento mantenimiento){
        Optional<Mantenimiento> optionalMantenimiento = mantenimientoRepository.findById(mantenimiento.getId());
        if(optionalMantenimiento.isPresent()){
            Mantenimiento mantenimientoToUpdate = optionalMantenimiento.get();

            mantenimientoToUpdate.setFechaVencimiento(mantenimiento.getFechaVencimiento());
            mantenimientoToUpdate.setPago(mantenimiento.getPago());
            mantenimientoToUpdate.setFechaPago(mantenimiento.getFechaPago());
            mantenimientoToUpdate.setPeriodo(mantenimiento.getPeriodo());
//            mantenimientoToUpdate.setParcela(mantenimiento.getParcela());

            mantenimientoRepository.save(mantenimientoToUpdate);

            return mantenimientoToUpdate;
        }else{
            throw new ResourceNotFoundException("Mantenimiento no encontrado");
        }


    }

    private int obtenerPeriodo(String periodoMantenimiento ){
        String periodo = NombrePago.valueOf(periodoMantenimiento).toString();

        if (Objects.equals(periodo, "MENSUAL")){
            return 1;
        }else if (Objects.equals(periodo,"BIMESTRAL")){
            return 2;
        } else if (Objects.equals(periodo,"TRIMESTRAL")) {
            return 3;
        } else if (Objects.equals(periodo,"CUATRIMESTRAL")) {
            return 4;
        } else if (Objects.equals(periodo,"SEMESTRAL")) {
            return 6;
        } else if (Objects.equals(periodo,"ANUAL")) {
            return 12;
        }

        return 0;
    }

    private Date obtenerVencimiento(Integer cantidad){
        Date proximoVenc = new SimpleDateFormat("yyyy-MM-dd").get2DigitYearStart();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(Instant.now()));
        calendar.add(Calendar.MONTH,cantidad);

        return calendar.getTime();
    }
}
