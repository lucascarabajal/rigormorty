package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zonas")
@Setter @Getter @NoArgsConstructor
public class Zona implements Serializable {
    @Id
    private String nombreZona;

    @Column(nullable = false)
    private Integer cantidadParcela;

    @Column(nullable = false)
    private double precioZona;

    @Column(nullable = false)
    private Integer nivelMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"zonas", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_cementerio")
    private Cementerio cementerio;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL)
    private List<Parcela> parcelas = new ArrayList<>();

}
