package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "domicilios")
@Getter @Setter @NoArgsConstructor
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String calle;

    @Column
    private Integer cp;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(nullable = false, length = 90)
    private String provincia;

    @Column(nullable = false, length = 25)
    private String pais;

}