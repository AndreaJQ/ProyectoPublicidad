package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.Tarea;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ServiceTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarea")
public class TareaController {
    @Autowired
    private ServiceTarea serviciotarea;

    @GetMapping("/Tareas")
    public List ListaTareas(){
        return this.serviciotarea.tareasList();
    }
    @PostMapping("/createTarea")
    public Tarea crearTarea(@RequestBody String nombre,
                            @RequestBody estadoTarea estado,
                            @RequestBody Proyecto proyecto,
                            @RequestBody UserEntity agente,
                            @RequestBody Boolean baja )throws MyException{
            Tarea tarea=new Tarea();
        tarea=this.serviciotarea.CreateTarea(nombre,estado,proyecto,agente,baja);
        return tarea;
    }
    @PutMapping("/update")
    public Tarea UpDateTarea(@RequestBody Tarea request, @PathVariable("idTarea") String idTarea)throws MyException{

        return serviciotarea.UpdateTarea(idTarea, request);}

    @GetMapping(path="/{idTarea}")
    public Optional<Tarea> getTareaById(@PathVariable("idtarea") String  idTarea){
    return this.serviciotarea.getById(idTarea);
    }
    @DeleteMapping(path = "/{idTarea}")
    public Boolean deleteTarea(@PathVariable("idTarea")String idTarea) throws MyException {
       Boolean ok=this.serviciotarea.deleteTarea(idTarea);
        if (ok == true) {
            return true;
        }else {return false;}
           }

}