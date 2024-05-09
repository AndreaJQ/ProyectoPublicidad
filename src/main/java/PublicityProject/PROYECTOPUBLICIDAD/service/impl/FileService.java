package PublicityProject.PROYECTOPUBLICIDAD.service.impl;


import PublicityProject.PROYECTOPUBLICIDAD.entity.ArchivoAdjunto;

import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Transactional
    public ArchivoAdjunto guardar(MultipartFile archivo) throws MyException {
        if (!archivo.getContentType().equals("application/octet-stream")) {
            try {
                ArchivoAdjunto aux = new ArchivoAdjunto();
                aux.setTipo(archivo.getContentType());
                aux.setNombre(archivo.getName());
                aux.setContenido(archivo.getBytes());
                return fileRepository.save(aux);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return null;
    }

    @Transactional
    public ArchivoAdjunto actualizar(MultipartFile archivo, Long id) throws MyException {
        if (archivo != null) {
            try {
                ArchivoAdjunto aux = new ArchivoAdjunto();
                if (id != null) {
                    Optional<ArchivoAdjunto> respuesta = fileRepository.findById(id);
                    if (respuesta.isPresent()) {
                        aux = respuesta.get();
                    }
                }
                // lo pongo fuera del if, para que en caso que la imágen no exista, se cree una
                // nueva y la devuelva
                // (para evitar que se cargen nulas en la actualizacion de usuario y proyecto)
                aux.setTipo(archivo.getContentType());
                aux.setNombre(archivo.getName());
                aux.setContenido(archivo.getBytes());
                return fileRepository.save(aux);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) throws MyException {
        try {
            ArchivoAdjunto aux = new ArchivoAdjunto();
            if (id != null) {
                Optional<ArchivoAdjunto> respuesta = fileRepository.findById(id);
                if (respuesta.isPresent()) {
                    aux = respuesta.get();
                }
                fileRepository.delete(aux);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public ArchivoAdjunto getOne(Long id) {
        return fileRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    public ArchivoAdjunto getById(Long id) throws MyException {
        Optional<ArchivoAdjunto> aux = fileRepository.findById(id);
        if (aux.isPresent()) {
            return aux.get();
        }

        throw new MyException("Archivo no encontrada");
    }

    @Transactional(readOnly = true)
    public List<ArchivoAdjunto> listarTodos() {
        return fileRepository.findAll();
    }
}
