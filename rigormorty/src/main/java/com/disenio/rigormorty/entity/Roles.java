package com.disenio.rigormorty.entity;

import com.disenio.rigormorty.enums.NombreRol;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Roles implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private NombreRol nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"roles", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;
}
