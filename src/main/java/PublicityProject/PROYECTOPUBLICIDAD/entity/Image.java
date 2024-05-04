package PublicityProject.PROYECTOPUBLICIDAD.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import lombok.*;



@Data
@Table(name = "imagen")
@Getter
@Setter
@Builder
@Entity
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String mime;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMime() {
        return mime;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
}
