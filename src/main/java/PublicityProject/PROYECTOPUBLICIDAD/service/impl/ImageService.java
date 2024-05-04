package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Image;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ImageService {
    @Autowired
    private ImageRepository imagenRepositorio;

    @Transactional
    public Image createImagen(MultipartFile archivo) throws MyException, IOException {

        if (archivo != null) {
            try {
                Image imagen = new Image();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                new Exception(e.getMessage());
            }
        }
        return null;
    }

    public List ImagenList() {
        List list = imagenRepositorio.findAll();
        return list;
    }

    @Transactional
    public Image UpdateImage(MultipartFile archivo, String id) throws MyException, IOException {
        if (archivo != null) {
            Optional<Image> response = imagenRepositorio.findById(id);
            if (response.isPresent()) {
                Image image = response.get();
                image.setMime(archivo.getContentType());
                image.setNombre(archivo.getName());
                image.setContenido(archivo.getBytes());
                return imagenRepositorio.save(image);
            }
        }
        return null;
    }

    @Transactional
    public void DeleteImage(String id) throws MyException {
        Optional<Image> response = imagenRepositorio.findById(id);
        if (response.isPresent()) {
            Image image = response.get();
            imagenRepositorio.delete(image);
        } else {
            throw new MyException("La imagen con el ID proporcionado no existe.");
        }
    }

    public Image getImageById(String id) throws MyException {
        Optional<Image> response = imagenRepositorio.findById(id);
        if (response.isPresent()) {
            return response.get();
        } else {
            throw new MyException("La imagen con el ID proporcionado no existe.");
        }
    }


}