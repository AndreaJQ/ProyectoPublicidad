package PublicityProject.PROYECTOPUBLICIDAD.entity;
import java.util.Date;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(generator = "uuid")

    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(columnDefinition = "LONGTEXT")
    private String contenido;
    @ManyToOne
    private UserEntity usuario;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fecha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
