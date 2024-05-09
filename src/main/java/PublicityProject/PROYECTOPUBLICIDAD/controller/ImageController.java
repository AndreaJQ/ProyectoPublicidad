package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Image;

import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ImageService;
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
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Image createImage(@RequestParam("archivo") MultipartFile archivo) throws MyException, IOException {
        return imageService.createImagen(archivo);
    }

    @GetMapping("/list")
    public List<Image> listImages() {
        return imageService.ImagenList();
    }

   /*REVISAR PORQUE NO CARGABA IMAGEN
   @GetMapping("/load/{id}")
    public ResponseEntity<byte[]> loadImage(@PathVariable String id) throws MyException {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", image.getTipo())
                    .body(image.getContenido());
        } else {
            throw new MyException("La imagen con el ID proporcionado no existe.");
        }
    }*/

    //SI CARGA IMAGEN
    @GetMapping("/carga/{id}")
    public ResponseEntity<byte[]> imagenUsuario (@PathVariable Long id){
        UserEntity userEntity = userService.getUserById(id);

        byte[] image= userEntity.getImage().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);



        return new ResponseEntity<>(image,headers, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public Image updateImage(@PathVariable String id, @RequestParam("archivo") MultipartFile archivo) throws MyException, IOException {
        return imageService.UpdateImage(archivo, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteImage(@PathVariable String id) throws MyException {
        imageService.DeleteImage(id);
    }
}