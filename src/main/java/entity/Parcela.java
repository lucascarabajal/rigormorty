package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.NombreParcela;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parcelas")
@Getter @Setter @NoArgsConstructor
public class Parcela implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer nivelActual;

    @Column(nullable = false)
    private Integer numeroParcela;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"parcelaZ", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_zona")
    private Zona parcelaZ;

    //reveer mas adelante
    @OneToMany(mappedBy = "estados", cascade = CascadeType.ALL)
    private List<EstadoParcela> estados = new ArrayList<>();

}
