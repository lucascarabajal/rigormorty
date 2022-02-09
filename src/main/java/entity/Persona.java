package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private String dni;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNac;

    @Column(nullable = false)
    private Domicilio domicilio;

    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL)
    private List<Domicilio> domPers = new ArrayList<>();
}
