package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.Tarea;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.ProjectRepository;
import PublicityProject.PROYECTOPUBLICIDAD.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ServiceTarea {
    @Autowired
    protected TareaRepository tareareposirio;
    @Autowired
    protected ProjectRepository proyectoRepositorio;
    @Transactional
    public Tarea CreateTarea(Tarea request)
            throws MyException {

        Validacion(request);
        Tarea tarea = new Tarea();
        tarea.setNombre(request.getNombre());
        tarea.setEstadoTarea(request.getEstadoTarea());
        tarea.setProyectos(request.getProyectos());
        tarea.setAgente(request.getAgente());
        tarea.setComentario(request.getComentario());
        tareareposirio.save(tarea);

        return tarea;
    }

    public void Validacion(Tarea request)
            throws MyException {
        if (request.getNombre().isEmpty() || request.getNombre() == null) {
            throw new MyException("el nombre esta vacio o es nulo.");
        }
        if (request.getEstadoTarea() == null) {
            throw new MyException("el estado no puede ser nulo");
        }

        if (request.getProyectos() == null) {
            throw new MyException("el proyecto esta vacio o es nulo");
        }
        if (request.getAgente() == null){
            throw new MyException("el agente esta vacio o es nulo");
        }
        if (request.getComentario() == null){
            throw new MyException("el agente esta vacio o es nulo");
        }

    }

    @Transactional
    public String UpdateTarea(Long idTarea, Tarea request) throws MyException {
        Validacion(request);
        Optional<Tarea> existe = tareareposirio.findById(idTarea);
        if (existe.isPresent()) {
            Tarea tarea = tareareposirio.getById(idTarea);
            tarea.setNombre(request.getNombre());
            tarea.setEstadoTarea(request.getEstadoTarea());
            tarea.setProyectos(request.getProyectos());
            tarea.setAgente(request.getAgente());
            tarea.setComentario(request.getComentario());
            tareareposirio.save(tarea);
            return "la tarea ha sido modificada";
        } else {
            throw new MyException("la tarea no existe");}


    }
    public List tareasList () {
        List list = tareareposirio.findAll();
        return list;
    }
    @Transactional
    public Boolean deleteTarea (Long idtarea)throws MyException {
        Optional<Tarea> tarea = tareareposirio.findById(idtarea);
        if (tarea.isPresent()) {
            this.tareareposirio.deleteById(idtarea);
            return true;
        } else {
            throw new MyException("el id no corresponde a una tarea existente");
        }
    }
}
