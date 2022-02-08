package entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDomicilio;

    @Column(nullable = false, length = 50)
    private String calle;

    @Column
    private int cp;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(nullable = false, length = 25)
    private String provincia;

    @Column(nullable = false, length = 25)
    private String pais;

    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL)
    @JsonManagedReference("domPers")
    private List<Persona> domPers;

}
