package com.disenio.rigormorty.models.responses;

import com.disenio.rigormorty.entity.Parcela;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ZonaResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombreZona;
    private Integer cantidadParcela;
    private Double precioZona;
    private Integer nivelMax;
    private Double precioMantenimiento;
}
