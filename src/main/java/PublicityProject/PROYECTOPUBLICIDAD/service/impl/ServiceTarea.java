package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.Tarea;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
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
    @Transactional
    public Tarea CreateTarea(String nombre, estadoTarea estado, Proyecto proyecto,UserEntity agente, Boolean baja)
    throws MyException
    {
        Validacion(nombre, estado, proyecto, agente, baja);
    Tarea tarea=new Tarea();
    tarea.setNombre(nombre);
    tarea.setProyecto(proyecto);
    tarea.setAgente(agente);
    tarea.setBaja(baja);
    return tareareposirio.save(tarea);
    }
    public void Validacion(String nombre,
                           estadoTarea estado,
                           Proyecto proyecto,
                           UserEntity agente,
                           Boolean baja)
    throws MyException
    {
        if (nombre.isEmpty()|| nombre==null){
            throw new MyException("el nombre esta vacio o es nulo.");
        }
        if (estado==null){
            throw new MyException("el estado no puede ser nulo");
        }
        if (proyecto==null){
            throw new MyException("el proyecto no puede ser nulo");
        }
        if (agente == null) {
            throw new MyException("el agente esta vacio o es nulo");
        }
        if (baja == null || baja==false) {
            throw new MyException("la baja tiene que ser verdadera o no puede ser nula");
        }
    }
    @Transactional
    public Tarea UpdateTarea(String idTarea, Tarea request)throws MyException{

        Optional<Tarea> tareas= tareareposirio.findById(idTarea);
        if (tareas.isPresent() ) {
        request.setNombre(request.getNombre());
        request.setEstadoTarea(request.getEstadoTarea());
        request.setProyecto(request.getProyecto());
        request.setAgente(request.getAgente());
        request.setBaja(request.getBaja());
        tareareposirio.save(request);
        }else {throw new MyException("la tarea esta vacia");
        }
        return request;

    }
    public List tareasList(){
        List list=tareareposirio.findAll();
        return list;
    }
    @Transactional
    public Boolean deleteTarea(String idtarea)throws MyException{
        Optional<Tarea>tarea=tareareposirio.findById(idtarea);
        if (tarea.isPresent() ) {
            this.tareareposirio.deleteById(idtarea);
            return true;
        }else{
            throw new MyException("el id no corresponde a una tarea existente");
        }
    }
    public Optional<Tarea> getById(String idTarea){
        return this.tareareposirio.findById(idTarea);
    }
}
