package PublicityProject.PROYECTOPUBLICIDAD.entity;

import PublicityProject.PROYECTOPUBLICIDAD.enumeration.ProjectStatus;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "proyecto")
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;

    @Column(columnDefinition = "LONGTEXT")
    @Basic(optional = true)
    private String descripcion;


    @ManyToMany
    private List<UserEntity> colaboradores;
    @Enumerated (EnumType.STRING)
    private ProjectStatus estado;

    private Boolean AltaBaja;
    @OneToOne
    @Basic(optional = true)
    private ArchivoAdjunto archivo;

    @Enumerated(EnumType.STRING)
    private AccessType visibilidad;

}
