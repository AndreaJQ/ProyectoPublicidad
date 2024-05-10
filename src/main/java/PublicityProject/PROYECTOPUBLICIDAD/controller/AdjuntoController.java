package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.ArchivoAdjunto;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/archivo")
public class AdjuntoController {

    @Autowired
    private FileService fileService;
    @GetMapping("/list")
    public List<ArchivoAdjunto> listFiles() {
        return fileService.listarTodos();
    }
    @GetMapping("/load/{id}")
    public ResponseEntity<byte[]> loadFile(@PathVariable String id) throws MyException {
        ArchivoAdjunto file = fileService.getById(id);
        if (file != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", file.getTipo())
                    .body(file.getContenido());
        } else {
            throw new MyException("El archivo con el ID proporcionado no existe.");
        }
    }
}
