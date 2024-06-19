package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Archivo;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class ArchivoService {
    @Autowired
    private ArchivoRepository archivoRepository;
    @Transactional
    public Archivo CreateFile(Archivo request)throws MyException {
        Validacion(request.getNombre(), request.getTipo(), request.getContenido());
        File archivo=new File(request.getNombre());
        File Tipo=new File(request.getTipo());

        try {
            PrintWriter Salida=new PrintWriter(archivo);
            PrintWriter SalidaTipo=new PrintWriter(Tipo);
            Salida.close();
            System.out.println("se creo el archivo");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
        Archivo file=new Archivo();
        file.setNombre(archivo.getName());
        file.setTipo(Tipo.getName());

        archivoRepository.save(file);


        return file;
    }
    @Transactional
    public Archivo WriteFile(Long id,Archivo request) throws MyException {
        Validacion(request.getNombre(), request.getContenido(), request.getTipo());
        File archivo = new File(request.getContenido());
        try {
           PrintWriter Salida=new PrintWriter(archivo);
            Salida.close();
            System.out.println("se creo el archivo");

        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        Optional<Archivo> arch = archivoRepository.findById(id);
        Archivo file = new Archivo();
        if (arch.isPresent()) {
            file=archivoRepository.getById(id);
            file.setContenido(request.getContenido());
            archivoRepository.save(file);
        }else {throw new MyException("Error el archivo no existe");}
        return file;
    }

    @Transactional
    public String DeleteFile(Long id) throws MyException {
        Optional<Archivo> file=archivoRepository.findById(id);
        if (file.isPresent()){
            Archivo archivoAdjunto=archivoRepository.getById(id);
            archivoRepository.delete(archivoAdjunto);
        } else {throw new MyException("Error!!, el archivo no esta presente") ;
        }
        return "el archivo fue eliminado";
    }
    public void Validacion(String nombre, String contenido, String Tipo) throws MyException {
        if(nombre.isEmpty() || nombre==null){
            throw new MyException("el archivo debe tener un nombre y no puede ser nulo");
        }
        if (contenido.isEmpty() || contenido==null){
            throw new MyException("el contenido esta vacio o es nulo");
        }
        if (Tipo.isEmpty() || Tipo==null){
            throw new MyException("el tipo esta vacio o es nulo");
        }
    }



    public List ListaDeArchivos(){
        List<Archivo> archivos=archivoRepository.findAll();
        return archivos;

    }
}
