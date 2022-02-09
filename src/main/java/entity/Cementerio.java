package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cementerio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCementerio;

    @Column(nullable = false,length = 60)
    private String nombreCementerio;

    @Column(nullable = false)
    private Domicilio ubicacion;

    @Column(nullable = false)
    private int zonas;

    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL)
    private List<Domicilio> domCem = new ArrayList<>();

}
