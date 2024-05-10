package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.*;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.Visibilidad;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.ProjectStatus;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.ArchivoRepository;
import PublicityProject.PROYECTOPUBLICIDAD.repository.ProjectRepository;
import PublicityProject.PROYECTOPUBLICIDAD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
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
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private ArchivoRepository archivoRepository;
    @Autowired
    private ProjectRepository pRepository;



    //------------------------CREATE--------------------------
   @Transactional
    public void create (Proyecto proyecto,
                        Long userId,
                        MultipartFile archivo
                        //Long idColab
                        ) throws MyException, IOException {
        //validate();
       UserEntity user = new UserEntity();
       Optional <UserEntity> answer = userRepository.findById(userId);
       if (answer.isPresent()){
           user= answer.get();
           proyecto.setPropietario(user);

           proyecto.setFecha(new Date());
           proyecto.setEstado(ProjectStatus.TODO);
           proyecto.setAltaBaja(false);

           // List<UserEntity> colaboradores = new ArrayList<>();
            //colaboradores.add(userService.getOne(idColab));

          ArchivoAdjunto file = fileService.guardar(archivo);
           proyecto.setArchivo(file);

           pRepository.save(proyecto);
       }
    }
//^evita escribir tantas lineas
    /*@Transactional
    public void guardar(MultipartFile archivo,
                       String nombre, AccessType visibilidad,
                        String notas, Long idCliente, Long idUsuario, Date fechaLimite) throws MyException {
        //validar(nombre, visibilidad, idUsuario, idCliente);

        Proyecto proyecto = new Proyecto();
        FileOptional file = fileService.guardar(archivo);
        List<UserEntity> usuarios = new ArrayList();
       /* usuarios.add(userService.getOne(idUsuario));
        usuarios.add(userService.getOne(idCliente));
        proyecto.setNombre(nombre);
        proyecto.setUsuarios(usuarios);
        proyecto.setVisibilidad(AccessType.PRIVATE);
        proyecto.setNotas(notas);
        proyecto.setFechaLimite(fechaLimite);
        proyecto.setFecha(new Date());
        proyecto.setArchivo(file);
        proyecto.setAltaBaja(Boolean.FALSE);
        projectRepository.save(proyecto);
    }*/


    //------------------------READ--------------------------
       @Transactional
    public List <Proyecto> list(){
        List<Proyecto> project = new ArrayList<>();
        project=projectRepository.findAll();
        return project;
    }

    public Optional<Proyecto> getProjectById (Long id){
        return projectRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Proyecto getOne(Long id) {
        return projectRepository.getOne(id);
    }

    //------------------------UPDATE--------------------------
   /* @Transactional
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
            }

            projectRepository.save(updateProject);
            return updateProject;
        }else {
            return null;
        }

    }*/


    //------------------------DELETE--------------------------
    @Transactional
    public void delete (Long id){
        Optional<Proyecto> response = projectRepository.findById(id);
        if (response!=null){
            Proyecto projectToDelete = response.get();
            projectRepository.delete(projectToDelete);
        }
    }
}