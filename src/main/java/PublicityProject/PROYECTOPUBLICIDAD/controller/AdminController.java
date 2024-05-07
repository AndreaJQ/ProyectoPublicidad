package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.Tarea;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ServiceTarea;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminController {

    private UserService userService;
    private ServiceTarea tareaService;

    @Autowired
    public AdminController(UserService userService, ServiceTarea tareaService) {
        this.userService = userService;
        this.tareaService=tareaService;
    }
    @GetMapping("/dashboard")
    public String panelAdmin(){

        return "dashboard";
    }
    //    U - - S - - E - - R - - S
//----------LIST USERS----------------
    @GetMapping("/users")
    public String listUser (ModelMap model){
        List<UserEntity> user = userService.list();
        model.addAttribute("user", user);
        List<Tarea> tarea = tareaService.tareasList();
        model.addAttribute("tarea", tarea);
        //List<Proyecto> projects = projectService.list();
        //model.addAttribute("proyectos", projects);
        return "users-list";
    }

    //----------CHANGE ROLE USER----------------
    @GetMapping("/editRole/{id}")
    public String changeRol(@PathVariable Long id){
        userService.changeRole(id);
        return "redirect:/admin/users";
    }
    @PostMapping("/editRole/{id}")
    public String changeRole(@PathVariable Long id){
        userService.changeRole(id);
        return "redirect:/admin/users";
    }
    //----------DELETE USERS----------------
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model){
        try{
            userService.delete(id);
            return "redirect:/admin/users";
        } catch (Exception ex){
            model.addAttribute("error", "No se puede eliminar el Usuario porque se encuentra vinculado a una publicación o comentario");
            return "error-deleteUser";
        }

    }
    //----------CHANGE STATUS USERS----------------
    @GetMapping("/editUserStatus/{id}")
    public String changeUserStat(@PathVariable Long id){
        userService.changeUserStatus(id);
        return "redirect:/admin/users";
    }
    //----------CHANGE STATUS USERS----------------
    @PostMapping("/editUserStatus/{id}")
    public String changeUserStatus(@PathVariable Long id){
        userService.changeUserStatus(id);
        return "redirect:/admin/users";
    }



}