package PublicityProject.PROYECTOPUBLICIDAD.entity;


import PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Tarea {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "uuid2")
    private long idTarea;
    private String tarea;
    @Enumerated(EnumType.STRING)
    private estadoTarea estadoTarea;
    private Proyecto proyecto;
   /* @ManyToOne
    private Agente agente;*/

    public Tarea() {
    }

    public Tarea(long idTarea, String tarea, PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea estadoTarea, Proyecto proyecto) {
        this.idTarea = idTarea;
        this.tarea = tarea;
        this.estadoTarea = estadoTarea;
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
