package com.disenio.rigormorty.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@MappedSuperclass
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String nombre;

    @Column(nullable = false, length = 25)
    private String apellido;

    @Column(nullable = false, length = 9)
    private Integer dni;

    @Column
    @Temporal(TemporalType.DATE)
    private Date fechaNac;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Domicilio> domicilios = new ArrayList<>();
}
