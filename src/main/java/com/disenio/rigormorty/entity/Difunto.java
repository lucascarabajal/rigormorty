package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "difuntos")
@Getter @Setter @NoArgsConstructor
public class Difunto extends Persona implements Serializable {
    @Column(nullable = false)
    private LocalDate fechaDef;

    @Column(nullable = false, length = 50)
    private String numExpediente;

    @Column(nullable = false)
    private Integer numNivel;

    @Column(length = 100)
    private String acta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parcela",nullable = false)
    private Parcela parcela;
}
