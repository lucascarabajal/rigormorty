package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 75)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Roles rol;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    List<RegistroCompra> registroCompras = new ArrayList<>();
}