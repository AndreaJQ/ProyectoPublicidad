package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Archivo;
import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.AccessType;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.ProjectStatus;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.ProjectRepository;
import PublicityProject.PROYECTOPUBLICIDAD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ArchivoService archivoService;

    //------------------------CREATE--------------------------
    @Transactional
   public void create (Proyecto project, Long userId,
                        List<File> archivo) throws MyException, IOException {
        UserEntity user = new UserEntity();
        Optional<UserEntity> answer = userRepository.findById(userId);

        /*if (answer.isPresent()){
            user = answer.get();
            project.setUsuarios((List<UserEntity>) user);

            List<Archivo> files = new ArrayList<>();
            //for (File archivos : archivo){
              //  Archivo file = archivoService.CreateFile(String.valueOf(archivos));
                //archivo.add(file);
            }
            //CORREGIR LINEAS PORQUE TIENE QUE SER ENTIDAD ARCHIVO - NO IMAGEN
            project.setArchivo((Archivo) files);
            projectRepository.save(project);
        }*/

    }

    //------------------------READ--------------------------
    public List <Proyecto> list(){
        List<Proyecto> project = new ArrayList<>();
        project=projectRepository.findAll();
        return project;
    }

    public Optional<Proyecto> getProjectById (String id){
        return projectRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Proyecto getOne(String id) {
        return projectRepository.getOne(id);
    }

    //------------------------UPDATE--------------------------
    @Transactional
    public Proyecto updateProject (File archivos, String id,
                                   //List<MultipartFile> archivos,
                                   String nombre,
                                   Date fecha,
                                   Date fechaLimite,
                                   String notas,
                                   ProjectStatus estado,
                                   AccessType visibilidad) throws MyException, IOException {
        Optional<Proyecto> optionalProyecto= projectRepository.findById(id);
        if (optionalProyecto.isPresent()) {
            Proyecto updateProject = optionalProyecto.get();
            updateProject.setNombre(nombre);
            updateProject.setFecha(fecha);
            updateProject.setFechaLimite(fechaLimite);
            //updateProject.setUsuarios(getProjectById(id).get().getUsuarios());
            updateProject.setNotas(notas);
            //corregir la linea con el servicio de archivos
            updateProject.setEstado(estado);

            //updateProject.setVisibilidad(AccessType);

            /*if( archivos!= null && !archivos.isEmpty()){
                List<Image> updateImages = new ArrayList<>();
                for (MultipartFile archivo : archivos){
                    Image newImage= imageService.createImagen(archivo);
                    updateImages.add(newImage);
                }
                updateProject.setArchivo((Archivo) archivos);
            }*/

            projectRepository.save(updateProject);
            return updateProject;
        }else {
            return null;
        }

    }


    //------------------------DELETE--------------------------
    @Transactional
    public void delete (String id){
        Optional<Proyecto> response = projectRepository.findById(id);
        if (response!=null){
            Proyecto projectToDelete = response.get();
            projectRepository.delete(projectToDelete);
        }

    }



}
