package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
   @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String listUser (ModelMap model){
        List<UserEntity> user = userService.list();
        model.addAttribute("user", user);
        return "listUsers";
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
}
