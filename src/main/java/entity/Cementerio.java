package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Cementerio implements Serializable {
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

    @OneToMany(mappedBy = "zonas", cascade = CascadeType.ALL)
    private List<Zona> zonaCem = new ArrayList<>();

}
