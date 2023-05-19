package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.enums.NombrePago;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.MantenimientoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class MantenimientoServiceImpl implements MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private static final Map<String, Integer> PERIODOMAP = new HashMap<>();

    @Override
    public ResponseEntity<Object> addMantenimiento(Mantenimiento mantenimiento){
        int periodo = obtenerPeriodo(mantenimiento.getPeriodo());

        mantenimiento.setFechaPago(LocalDate.now());
        mantenimiento.setFechaVencimiento(getVencimiento(periodo));
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
        return PERIODOMAP.getOrDefault(periodo, 0);
    }

    private LocalDate getVencimiento(Integer cantidad){
        LocalDate localDate = LocalDate.now();
        return localDate.plusMonths(cantidad);
    }

    static {
        PERIODOMAP.put("MENSUAL", 1);
        PERIODOMAP.put("BIMESTRAL", 2);
        PERIODOMAP.put("TRIMESTRAL", 3);
        PERIODOMAP.put("CUATRIMESTRAL", 4);
        PERIODOMAP.put("SEMESTRAL", 6);
        PERIODOMAP.put("ANUAL", 12);
    }
}
