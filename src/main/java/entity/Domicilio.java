package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "domicilios")
@Getter @Setter @NoArgsConstructor
public class Domicilio implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDomicilio;

    @Column(nullable = false, length = 50)
    private String calle;

    @Column
    private Integer cp;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(nullable = false, length = 25)
    private String provincia;

    @Column(nullable = false, length = 25)
    private String pais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"domicilio", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona domicilios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"ubicaciones", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_cementerio", nullable = false)
    private Cementerio ubicaciones;


}
