package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Image;

import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create")
    public Image createImage(@RequestParam("archivo") MultipartFile archivo) throws MyException, IOException {
        return imageService.createImagen(archivo);
    }

    @GetMapping("/list")
    public List<Image> listImages() {
        return imageService.ImagenList();
    }

    @GetMapping("/load/{id}")
    public ResponseEntity<byte[]> loadImage(@PathVariable String id) throws MyException {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", image.getMime())
                    .body(image.getContenido());
        } else {
            throw new MyException("La imagen con el ID proporcionado no existe.");
        }
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