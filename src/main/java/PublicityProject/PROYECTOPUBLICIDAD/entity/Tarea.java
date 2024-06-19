package PublicityProject.PROYECTOPUBLICIDAD.entity;


import PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Table(name = "tarea")
@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;
    @Column
    private String nombre;
    @Column
    private estadoTarea estadoTarea;
    @ElementCollection
    private List<Proyecto> Proyectos;
    @ElementCollection
    private List<String> comentario;
    @ManyToOne
    private UserEntity agente;

    public Tarea() {
    }

    public Tarea(Long idTarea, String nombre, estadoTarea estadoTarea, List<Proyecto> proyectos, List<String> comentario, UserEntity agente1) {
        this.idTarea = idTarea;
        this.nombre = nombre;
        this.estadoTarea = estadoTarea;
        Proyectos = proyectos;
        this.comentario = comentario;
        this.agente = agente1;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public estadoTarea getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(estadoTarea estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public List<Proyecto> getProyectos() {
        return Proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        Proyectos = proyectos;
    }

    public List<String> getComentario() {
        return comentario;
    }

    public void setComentario(List<String> comentario) {
        this.comentario = comentario;
    }

    public UserEntity getAgente() {
        return agente;
    }

    public void setAgente(UserEntity agente) {
        this.agente = agente;
    }
}
