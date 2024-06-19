package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Imagen;

import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ImagenService;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/image")
public class ImageController {


    @Autowired
    private UserService userService;

    @Autowired
    private ImagenService imageService;


    @PostMapping("/create")
    public String createImage(@RequestParam("Imagen[]") MultipartFile request) throws MyException, IOException {
        imageService.createImagen(request);
        return "la imagen ha sido creada";
    }

    @GetMapping(value = "/listaImagenes")
    public List<Imagen> listImages() {
        return imageService.ImagenList();
    }




   /* @GetMapping("/carga/{id}")
    public ResponseEntity<byte[]> imagenUsuario (@PathVariable Long id){
        UserEntity userEntity = userService.getUserById(id);

       byte[] image= userEntity.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);



        return new ResponseEntity<>(Imagen,headers, HttpStatus.OK);
    }*/


    @PutMapping(value = "/update/{id}")
    public String  updateImage(@PathVariable Long id, @RequestParam("Imagen[]") MultipartFile archivo) throws MyException, IOException {
        imageService.UpdateImage(archivo, id);
        return "La imagen ha sido actualizada";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteImage(@PathVariable Long id) throws MyException {
        imageService.DeleteImage(id);
        return "la imagen ha sido eliminada";
    }

}