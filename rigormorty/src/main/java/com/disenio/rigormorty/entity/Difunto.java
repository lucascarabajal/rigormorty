package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "difuntos")
@Getter @Setter @NoArgsConstructor
public class Difunto extends Persona implements Serializable {
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDef;

    @Column(nullable = false, length = 50)
    private String numExpediente;

    @Column(nullable = false)
    private Integer numNivel;

    @Column(length = 100)
    private String acta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"difuntos", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_parcela",nullable = false)
    private Parcela parcela;

    @OneToMany(mappedBy = "difunto", cascade = CascadeType.ALL)
    private List<Domicilio> domicilios = new ArrayList<>();

}
