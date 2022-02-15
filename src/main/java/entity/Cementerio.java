package entity;

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
    private Domicilio ubicacion;

    @Column(nullable = false)
    private Integer cantZonas;

    @OneToMany(mappedBy = "ubicaciones", cascade = CascadeType.ALL)
    private List<Domicilio> ubicaciones = new ArrayList<>();

    @OneToMany(mappedBy = "zonas", cascade = CascadeType.ALL)
    private List<Zona> zonasC = new ArrayList<>();

}
