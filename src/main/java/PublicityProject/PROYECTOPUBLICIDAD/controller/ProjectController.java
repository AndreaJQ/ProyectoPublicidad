package PublicityProject.PROYECTOPUBLICIDAD.controller;
import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.Visibilidad;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ProjectService;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@Controller
@RequestMapping("/")
public class ProjectController {

    @Autowired
    private ProjectService pService;
    @Autowired
    private UserService userService;



    //-------------------CREATE PROJECT---------------------------
   @GetMapping("/proyecto")
    public String newproject(ModelMap model, HttpSession session){
        UserEntity user = (UserEntity) session.getAttribute("usuariosession");
        Proyecto project = new Proyecto();

        model.put("proyecto", project);
        model.put("user", user);


       List<UserEntity> colab=userService.list();
       model.addAttribute("colaborador", colab);

        return "Formulario_Proyecto.html";
    }
    @PostMapping("/newProyecto")
    public String saveProject(@ModelAttribute("proyecto") Proyecto project,
                              HttpSession session,
                              ModelMap modelMap,
                             BindingResult result,
                              @RequestParam ("archivos") MultipartFile archivos
                              //Long idColab
    ) throws MyException, IOException, ParseException {

        UserEntity user = (UserEntity) session.getAttribute("usuariosession");
        modelMap.put("user", user);
        Long userId = user.getId();


        if (result.hasErrors()){
            modelMap.put("proyecto", project);
           modelMap.put("user", user);
            return "Formulario_Proyecto.html";
        }


        pService.create(project, userId, archivos);
        return "redirect:/projectlist";

    }




    //---------------------------READ-----------------------LIST

    @GetMapping("/projectlist")
    public String projectList(Model model, Authentication authentication){

        List<Proyecto> proyecto = pService.list();
        model.addAttribute("proyecto",proyecto);

        return "project-list.html";
    }

    //proyecto by user
/*

    //----------------------READ-----------------------DETAIL
    @GetMapping("/proyecto/{projectId}")
    public String projectId(@PathVariable("projectId") String projectId, Model model, ModelMap modelo){
       Proyecto project =pService.getOne(projectId);

        //List<Tarea> tareas = tareaService.listarTareasProyecto(projectId);
        model.addAttribute("proyecto", project);
        //model.addAttribute("tareas", tareas);

       // tareas.forEach(tarea -> tarea.ordernarComentarios());


        return "Vista_formulario_Servicios3.html";
    }



    // --------------------UPDATE------------------------------
  /*  @PostMapping("/modificar")
    public String modificar(@RequestParam String projectId, @RequestParam String nombre, @RequestParam String notas,
                             @RequestParam File archivos,
                            @RequestParam Date fecha, @RequestParam Date fechalimite,
                            @RequestParam ProjectStatus estado, AccessType visibilidad,
                            ModelMap modelo,
                            HttpSession session) {

        Proyecto project =pService.getOne(projectId);
        try {
            pService.updateProject(archivos,
                    projectId, nombre, fecha, fechalimite,notas, estado, visibilidad);


            session.setAttribute("exito", "Proyecto modificado con exito!");
        } catch (MyException | IOException e) {
            session.setAttribute("error", e.getMessage());
        }

        return "redirect:/proyecto";
    }



    //---------------------------DELETE-------------------------
    @GetMapping("/proyectoerase/{proId}")
    public String deletePro(@PathVariable("proId") String proId){
        pService.delete(proId);
        return "redirect:/proyectoByUser";
    }*/
}
