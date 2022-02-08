package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter @Setter @NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String nombre;

    @Column(nullable = false, length = 25)
    private String apellido;

    @Column(nullable = false, length = 9)
    private String dni;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNac;

    @Column(nullable = false)
    private Domicilio domicilio;

    @ManyToOne
    @JsonManagedReference("persDom")
    @JoinColumn(name = "fkDom", nullable = false)
    private Domicilio persDom;

}
