package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.EstadoParcela;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.enums.NombreParcela;
import com.disenio.rigormorty.exception.EqualObjectException;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.responses.ZonaResponse;
import com.disenio.rigormorty.repository.ParcelaRepository;
import com.disenio.rigormorty.repository.ZonaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ZonaServiceImpl implements ZonaService{
    private final ZonaRepository zonaRepository;

    private final ParcelaRepository parcelaRepository;

    private ModelMapper mapper;

    @Override
    @Transactional
    public ResponseEntity<Zona> addZona(Zona zona) {

        if (Objects.nonNull(zonaRepository.findZonaByNombreZona(zona.getNombreZona()))) throw new EqualObjectException("Ya existe zona con este nombre");

        // Guardar la instancia de Zona en la base de datos
        Zona newZona = zonaRepository.save(zona);

        // Generar las parcelas y establecer la relación con la instancia de Zona guardada
        newZona.setParcelas(generarParcelas(newZona));
        newZona = zonaRepository.save(newZona);

        return ResponseEntity.ok(newZona);
    }

    @Override
    public ResponseEntity<List<ZonaResponse>> getZonas(){
        List<Zona> zonas = zonaRepository.findAll();
        List<ZonaResponse> response = zonas.stream().map(zona -> this.mapper.map(zona,ZonaResponse.class)).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public Object updateZona(Zona zona){
        Optional<Zona> optionalZona = zonaRepository.findById(zona.getId());
        if(optionalZona.isPresent()){
            Zona zonaToUpdate = optionalZona.get();

            zonaToUpdate.setPrecioZona(zona.getPrecioZona());
            zonaToUpdate.setCantidadParcela(zona.getCantidadParcela());
            zonaToUpdate.setNivelMax(zona.getNivelMax());
            zonaToUpdate.setParcelas(zona.getParcelas());

            zonaRepository.save(zonaToUpdate);

            return zonaToUpdate;
        }else{
            throw new ResourceNotFoundException("Zona no encontrada");
        }
    }

    @Override
    public void addListZona(List<Zona> zonas){
        for (Zona zona : zonas) {
            zona.setParcelas(generarParcelas(zona));
        }
        zonaRepository.saveAll(zonas);
    }

    @Override
    public ZonaResponse findById(Long id) {
        Optional<Zona> zona = zonaRepository.findById(id);

        if (zona.isPresent()){
            return zonaRepository.findZonaByNombreZona(zona.get().getNombreZona());
        }else{
            throw new ResourceNotFoundException("No se encuentra zona con ese id");
        }
    }

    private List<Parcela> generarParcelas(Zona zona){

        List<Parcela> parcelas = new ArrayList<>();

        for(int i = 0; i < zona.getCantidadParcela(); i++){
            Parcela parcela = new Parcela();
            List<EstadoParcela> estadoParcelas = new ArrayList<>();

            parcela.setNumeroParcela(zona.getNombreZona()+(i+1));
            parcela.setNivelMax(zona.getNivelMax());

            for(int j = 0; j < zona.getNivelMax(); j++){
                EstadoParcela estadoParcela = new EstadoParcela();
                estadoParcela.setEstadoParcela(NombreParcela.ESTADO_PARCELA_LIBRE);
                estadoParcelas.add(estadoParcela);
            }

            parcela.setEstados(estadoParcelas);

            // Guardar la instancia de Parcela en la base de datos
            parcela = parcelaRepository.save(parcela);

            parcelas.add(parcela);
        }

        return parcelas;
    }
}
