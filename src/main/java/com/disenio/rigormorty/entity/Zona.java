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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreZona;

    @Column(nullable = false)
    private Integer cantidadParcela;

    @Column(nullable = false)
    private double precioZona;

    @Column(nullable = false)
    private Integer nivelMax;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zona", referencedColumnName = "id")
    private List<Parcela> parcelas = new ArrayList<>();

}
