package PublicityProject.PROYECTOPUBLICIDAD.entity;

import PublicityProject.PROYECTOPUBLICIDAD.enumeration.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Data
@Table(name = "usuarios")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Proyecto {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;

    @Column(columnDefinition = "LONGTEXT")
    @Basic(optional = true)
    private String notas;

    @ManyToMany
    private List<UserEntity> usuarios;
    @Enumerated (EnumType.STRING)
    private ProjectStatus estado;

    private Boolean AltaBaja;
    @Basic(optional = true)
    private Archivo archivo;

    @Enumerated(EnumType.STRING)
    private AccessType visibilidad;
    public void addUsuario(UserEntity usuario) {
        this.usuarios.add(usuario);
    }

}
