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
    public Archivo CreateFile(String nombre)throws MyException{
        ValidacionNombre(nombre);
        File archivo=new File(nombre);
        try {
            PrintWriter Salida=new PrintWriter(archivo);
            Salida.close();
            System.out.println("se creo el archivo");
            } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    Archivo file=new Archivo();
        file.setNombre(archivo.getName());
        archivoRepository.save(file);
        return file;
    }
    @Transactional
    public Archivo WriteFile(String idArchivo,String nombre, String contenido) throws MyException {
        Validacion(nombre, contenido);
        File archivo = new File(nombre);
        try {
            PrintWriter Salida = new PrintWriter(new FileWriter(archivo, true));
            Salida.println(contenido);
            Salida.close();
            System.out.println("se escribio en el archivo");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        Optional<Archivo> arch = archivoRepository.findById(idArchivo);
        Archivo file = new Archivo();
        if (arch.isPresent()) {
            file.setNombre(arch.get().getNombre());
            file.setContenido(arch.get().getContenido());
            archivoRepository.save(file);
        }else {throw new MyException("Error el archivo no existe");}
        return file;
    }

    public String readFile(String nombre, String contenido) throws MyException {
        Validacion(nombre, contenido);
        File archivo=new File(nombre);
        try {
            BufferedReader read=new BufferedReader(new FileReader(archivo));
            String lectura=read.readLine();
            while (lectura != null){
                System.out.println(lectura);
                lectura=read.readLine();
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "La lectura fue realizada";
    }
@Transactional
public String DeleteFile(String idArchivo) throws MyException {
          Optional<Archivo> file=archivoRepository.findById(idArchivo);
           if (file.isPresent()){
               File arch=new File(idArchivo);
               arch.delete();
               archivoRepository.deleteById(idArchivo);
           } else {throw new MyException("Error!!, el archivo no esta presente") ;
           }
       return "el archivo fue eliminado";
}
public void Validacion(String nombre, String contenido)throws MyException{
    if(nombre.isEmpty() || nombre==null){
            throw new MyException("el archivo debe tener un nombre y no puede ser nulo");
        }
        if (contenido.isEmpty() || contenido==null){
            System.out.println("el contenido esta vacio o es nulo");
        }
}
    public void ValidacionNombre(String nombre)throws MyException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("el archivo debe tener un nombre y no puede ser nulo");
        }

    }
    public List ListaDeArchivos(){
        List<Archivo> archivos=archivoRepository.findAll();
        return archivos;
    }
}
