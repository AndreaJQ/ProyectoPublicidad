package PublicityProject.PROYECTOPUBLICIDAD.controller;
import PublicityProject.PROYECTOPUBLICIDAD.entity.ArchivoAdjunto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.Visibilidad;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ProjectService;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

       List<UserEntity> colaborador=userService.list();
       model.addAttribute("colaborador", colaborador);

        return "Formulario_Proyecto.html";
    }
    @PostMapping("/newProyecto")
    public String saveProject(@ModelAttribute("proyecto") Proyecto project,
                              HttpSession session,
                              ModelMap modelMap,
                              BindingResult result,
                              @RequestParam ("archivos") MultipartFile archivos,
                              @RequestParam("idColabs") List<Long> idColab) throws MyException, IOException, ParseException {

        UserEntity user = (UserEntity) session.getAttribute("usuariosession");
        modelMap.put("user", user);
        Long userId = user.getId();

        if (result.hasErrors()){
            modelMap.put("proyecto", project);
            modelMap.put("user", user);
            return "Formulario_Proyecto.html";
        }

        Proyecto nuevoProyecto = pService.create(project, userId, archivos,idColab);
        Long projectId = nuevoProyecto.getId();
        return "redirect:/proyecto/" + projectId;
    }


    //---------------------------READ-----------------------LIST

    @GetMapping("/projectlist")
    public String projectList(Model model, Authentication authentication){

        List<Proyecto> proyecto = pService.list();
        model.addAttribute("proyecto",proyecto);

        return "project-list.html";
    }

    //proyecto by user


    //----------------------READ-----------------------DETAIL
    @GetMapping("/proyecto/{pId}")
    public String projectId(@PathVariable("pId") Long projectId, Model model, ModelMap modelo){
       Proyecto project =pService.getOne(projectId);

        //List<Tarea> tareas = tareaService.listarTareasProyecto(projectId);
        model.addAttribute("proyecto", project);
        //model.addAttribute("tareas", tareas);

       // tareas.forEach(tarea -> tarea.ordernarComentarios());


        return "project-detail.html";
    }



    // --------------------UPDATE------------------------------
    @GetMapping("/proyectoEdit/{proId}")
    public String projectDetail(@PathVariable("proId") Long proId, Model model) {
        Optional<Proyecto> optionalProyecto = pService.getProjectById(proId);
        Proyecto proyecto = optionalProyecto.orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado para el ID: " + proId));
        UserEntity user = proyecto.getPropietario();
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("proId", proId);
        model.addAttribute("user", user);
        List<UserEntity> colaborador=userService.list();
        model.addAttribute("colaborador", colaborador);
        return "project-form.html";
    }

    @PostMapping("/proyectoEdit/{proId}")
    public String modificar(@PathVariable("proId") Long proId,
                            @RequestParam String nombre,
                            @RequestParam String descripcion,
                            @RequestParam("archivo") MultipartFile archivos,
                            @RequestParam("fechaLimite") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date fechalimite, // Cambiar el tipo de dato a String
                            @RequestParam Visibilidad visibilidad,
                            @RequestParam List<Long> colaborador,
                            HttpSession session) throws MyException, IOException {

            Proyecto updatedProject=
            pService.updateProject(proId, nombre, descripcion, visibilidad, archivos,fechalimite,colaborador);
            Long projectId= updatedProject.getId();
            session.setAttribute("exito", "Proyecto modificado con exito!");

        return "redirect:/proyecto/" + projectId;
    }

    //---------------------------DELETE-------------------------
    @GetMapping("/proyectoerase/{proId}")
    public String deletePro(@PathVariable("proId") Long proId){
        pService.delete(proId);
        return "redirect:/proyectoByUser";
    }
}
