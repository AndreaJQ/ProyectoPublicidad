package PublicityProject.PROYECTOPUBLICIDAD.entity;

import PublicityProject.PROYECTOPUBLICIDAD.enumeration.ProjectStatus;

import PublicityProject.PROYECTOPUBLICIDAD.enumeration.Visibilidad;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "proyecto")
@Entity
@AllArgsConstructor

@Getter
@Setter
@Builder
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity propietario;
    private String nombre;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;

    @Column(columnDefinition = "LONGTEXT")
    @Basic(optional = true)
    private String descripcion;


    @ManyToMany
    private List<UserEntity> colaborador;
    @Enumerated (EnumType.STRING)
    private ProjectStatus estado;

    private boolean AltaBaja;
    @OneToOne
    @Basic(optional = true)
    private ArchivoAdjunto archivo;

    @Enumerated(EnumType.STRING)
    private Visibilidad visibilidad;

    public Proyecto() {
        this.colaborador = new ArrayList<>();
    }


}
