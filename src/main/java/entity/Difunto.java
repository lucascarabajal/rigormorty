package entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"difuntos", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_parcela",nullable = false)
    private Parcela parcela;
}
