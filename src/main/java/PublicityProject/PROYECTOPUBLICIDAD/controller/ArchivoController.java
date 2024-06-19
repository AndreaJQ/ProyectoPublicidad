package PublicityProject.PROYECTOPUBLICIDAD.controller;


import PublicityProject.PROYECTOPUBLICIDAD.entity.Archivo;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/archivo")
public class ArchivoController {
 @Autowired
 private ArchivoService archivoService;

 @GetMapping(value = "/ListaArchivos")
 public List ListaArchivos() {
  return this.archivoService.ListaDeArchivos();
 }

 @PostMapping(value = "/crearArchivo")
 @ResponseStatus(HttpStatus.CREATED)
 public String CreateFile(@RequestBody Archivo request) throws MyException {
  this.archivoService.CreateFile(request);
  return "Archivo creado";}

 @PutMapping(value = "/escribirArchivo/{id}")
 public String WriteFile (@PathVariable Long id,@RequestBody Archivo request) throws  MyException {
  this.archivoService.WriteFile(id,request);
  return "el archivo fue se escribio";
 }

 @DeleteMapping(value = "/borrarArchivo/{id}")
 public String deleteFile (@PathVariable Long id) throws MyException {
  this.archivoService.DeleteFile(id);
return "el archivo fue eliminado"; }
}
