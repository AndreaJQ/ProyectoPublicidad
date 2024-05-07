package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index ( ){
        return "index2.html";
    }


    @GetMapping("/registrarse")
    public String registrarse(ModelMap model){
        UserEntity us = new UserEntity();
        model.put("userEntity",us);
        return "registro.html";
    }
    @PostMapping("/newuser")

    public String saveUser(UserEntity userEntity, String password, String password2, MultipartFile archivo , BindingResult result, ModelMap model ) throws Exception {
        try {
            userService.create(userEntity, password,password2, archivo);


            model.addAttribute("exito", "Usuario registrado correctamente!");

            return "login.html";
        } catch (MyException ex) {

            model.put("error", ex.getMessage());

            return "registro.html";
        }
    }

    @GetMapping("/ingresar")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null){
            modelo.addAttribute("error","Usuario o contrasena invalidos. <br> Intenta nuevamente!");
        }

        return "login.html";
    }

    @GetMapping("/inicio")
    public String inicio(){
        return "inicio.html";
    }

    @GetMapping("/perfil")
    public String perfil(){
        return "perfil_user.html";
    }

    @GetMapping("/perfil/editarPerfil")
    public String editarPerfil(){
        return "perfil_edit.html";
    }

}

