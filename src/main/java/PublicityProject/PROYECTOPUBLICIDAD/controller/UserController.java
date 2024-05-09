package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ProjectService;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
   @Autowired
    private UserService userService;
    @Autowired
    private ProjectService pService;


    @GetMapping("/users")
    public String listUser (ModelMap model){
        List<UserEntity> user = userService.list();
        model.addAttribute("user", user);
        return "users-list";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        try {
            userService.delete(id);
            return "redirect:/admin/users";
        } catch (Exception ex) {
            model.addAttribute("error", "No se puede eliminar el Usuario porque se encuentra vinculado a una publicación o comentario");
            return "error-deleteUser";
        }
    }
    @GetMapping("/perfil")
    public String profile(ModelMap modelo, HttpSession session){
        UserEntity user = (UserEntity) session.getAttribute("usuariosession");
        modelo.put("user", user);
        List<Proyecto> proyecto = pService.list();
        modelo.addAttribute("proyecto", proyecto);
        return "perfil_user.html";
    }

    //--------------------------UPDATE-----------------
    //@PreAuthorize("hasAnyRole('ROLE_USERAGENT', 'ROLE_CLIENTE', 'ROLE_ADMIN')")
    @GetMapping("/edituser/{userId}")
    public String editUser(@PathVariable("userId") long userId, ModelMap model, HttpSession session){

        UserEntity user = (UserEntity) session.getAttribute("usuariosession");

        model.addAttribute("user", user);
        return"perfil_edit.html";
    }

    //@PreAuthorize("hasAnyRole('ROLE_USERAGENT', 'ROLE_CLIENTE', 'ROLE_ADMIN')")

    @PostMapping("/edituser/{userId}")
    public String editUser(@PathVariable("userId") Long userId, @RequestParam String name, @RequestParam String lastName,
                           @RequestParam String address, @RequestParam String contact, ModelMap model,
                           MultipartFile archivo, HttpSession session) throws Exception {

        UserEntity updatedUser= userService.updateUser(userId,name,lastName,contact,address,archivo);
        session.setAttribute("usuariosession", updatedUser);
        return "redirect:/user/perfil";

    }
}
