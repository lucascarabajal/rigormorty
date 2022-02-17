package com.disenio.rigormorty.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cementerios")
@Getter @Setter @NoArgsConstructor
public class Cementerio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 60)
    private String nombre;

    @Column(nullable = false)
    private Integer cantZonas;

    @OneToMany(mappedBy = "cementerio", cascade = CascadeType.ALL)
    private List<Domicilio> domicilios = new ArrayList<>();

    @OneToMany(mappedBy = "cementerio", cascade = CascadeType.ALL)
    private List<Zona> zonas = new ArrayList<>();

}