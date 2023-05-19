package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.enums.NombrePago;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.responses.MantenimientoResponse;
import com.disenio.rigormorty.repository.MantenimientoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MantenimientoServiceImpl implements MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private static final Map<String, Integer> PERIODOMAP = new HashMap<>();
    private ModelMapper mapper;

    @Override
    public ResponseEntity<Object> addMantenimiento(Mantenimiento mantenimiento){
        int periodo = obtenerPeriodo(mantenimiento.getPeriodo());

        mantenimiento.setFechaPago(LocalDate.now());
        mantenimiento.setFechaVencimiento(getVencimiento(periodo));
        mantenimientoRepository.save(mantenimiento);

        return ResponseEntity.ok("Se pago correctamente");
    }

    @Override
    public ResponseEntity<List<MantenimientoResponse>> getMantenimientos(){
        return ResponseEntity.ok(mantenimientoRepository.findAll().stream().map(mantenimiento -> this.mapper.map(mantenimiento, MantenimientoResponse.class)).collect(Collectors.toList()));
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

    public List<MantenimientoResponse> getMantenimientoByCliente(Long id){
        return mantenimientoRepository.getMantenimientoByCliente_Id(id).stream().map(mantenimiento -> this.mapper.map(mantenimiento, MantenimientoResponse.class)).collect(Collectors.toList());
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
