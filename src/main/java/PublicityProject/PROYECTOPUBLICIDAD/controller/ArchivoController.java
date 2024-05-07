package PublicityProject.PROYECTOPUBLICIDAD.controller;


import PublicityProject.PROYECTOPUBLICIDAD.entity.Archivo;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/archivo")
public class ArchivoController {
    @Autowired
    ArchivoService archivoService;
 @GetMapping("/ListaArchivos")
 public List ListaArchivos(){
    return this.archivoService.ListaDeArchivos();
 }
 @PostMapping("/crearArchivo")
 @ResponseStatus(HttpStatus.CREATED)
 public Archivo CreateFile(@RequestBody Archivo request) throws MyException {
return this.archivoService.CreateFile(request.getNombre());
 }
 @PutMapping("/escribirArchivo")
 public Archivo WriteFile(@RequestBody Archivo request) throws MyException {
     return this.archivoService.WriteFile(request.getId(),request.getNombre(), request.getContenido());

 }
 @GetMapping("/leer")
 public String readFile(@RequestBody Archivo request) throws MyException {
return this.archivoService.readFile(request.getNombre(), request.getContenido());
 }
 @DeleteMapping("/borrarArchivo")
 public String deleteFile(@RequestBody Archivo request) throws MyException {
 return this.archivoService.DeleteFile(request.getId());
 }
}
