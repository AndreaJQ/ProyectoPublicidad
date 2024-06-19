package PublicityProject.PROYECTOPUBLICIDAD.controller;


import PublicityProject.PROYECTOPUBLICIDAD.entity.Tarea;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.TareaRepository;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ServiceTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/tarea")
public class TareaController {
    @Autowired
    private ServiceTarea serviciotarea;
    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private UserEntity usuario;
    @GetMapping("/Tareas")
    public String ListaTareas(ModelMap modelo){
         List<Tarea> tareas= serviciotarea.tareasList();
         modelo.addAttribute("tareas", tareas);
         return "tareas-list.html";
    }
    @PostMapping("/createTarea")
    public String crearTarea(@RequestBody Tarea request )throws MyException{
            Tarea tarea=new Tarea();
        tarea=this.serviciotarea.CreateTarea(request);
        return "la tarea se ha creado";
    }
    @PutMapping("/update")
    public String UpDateTarea(@RequestBody Tarea request, @PathVariable("idTarea") Long idTarea)throws MyException{
        serviciotarea.UpdateTarea(idTarea, request);
    return "la tarea se ha actualizado";}
    @GetMapping(path="/{idTarea}")
    public Tarea getTareaById(@PathVariable("idtarea") Long  idTarea){
    return this.tareaRepository.getById(idTarea);
    }
    @DeleteMapping(path = "/{idTarea}")
    public String deleteTarea(@PathVariable("idTarea")Long idTarea) throws MyException {
       this.serviciotarea.deleteTarea(idTarea);
        return "la tarea ha sido eliminada";}

}