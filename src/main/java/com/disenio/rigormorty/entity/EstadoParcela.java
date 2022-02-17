package com.disenio.rigormorty.entity;

import com.disenio.rigormorty.enums.NombreParcela;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "estado_parcela")
@Getter @Setter @NoArgsConstructor
public class EstadoParcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NombreParcela estadoParcela;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"estados", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_parcela",nullable = false)
    private Parcela parcela;
}
