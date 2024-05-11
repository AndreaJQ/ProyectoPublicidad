package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.*;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.Visibilidad;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.ProjectStatus;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.ArchivoRepository;
import PublicityProject.PROYECTOPUBLICIDAD.repository.FileRepository;
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
    private FileRepository fileRepository;
    @Autowired
    private ArchivoRepository archivoRepository;
    @Autowired
    private ProjectRepository pRepository;



    //------------------------CREATE--------------------------
   @Transactional
    public void create (Proyecto proyecto,
                        Long userId,
                        MultipartFile archivo,
                        List<Long> idColabs
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

           List<UserEntity> colaboradores = userService.getAllById(idColabs);
           proyecto.getColaborador().addAll(colaboradores);

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
   @Transactional
    public Proyecto updateProject (Long id,
                                   String nombre,
                                   String descripcion,
                                   Visibilidad visibilidad,MultipartFile archivos,
                                   List<Long> idColabs) throws MyException, IOException {
        Optional<Proyecto> optionalProyecto= projectRepository.findById(id);
        if (optionalProyecto.isPresent()) {
            Proyecto updateProject = optionalProyecto.get();
            updateProject.setNombre(nombre);
            updateProject.setDescripcion(descripcion);
            updateProject.setFecha(updateProject.getFecha());
            updateProject.setVisibilidad(visibilidad);
           // updateProject.setFechaLimite(fechaLimite);
            List<UserEntity> colaboradores = new ArrayList<>();
            colaboradores.addAll(userService.getAllById(idColabs));
            updateProject.getColaborador().addAll(colaboradores);

            if (archivos != null && !archivos.isEmpty()) {
                ArchivoAdjunto nuevoArchivo = new ArchivoAdjunto();
                fileRepository.save(nuevoArchivo);
                updateProject.setArchivo(nuevoArchivo);
            }
            projectRepository.save(updateProject);
            return updateProject;
        }else {
            return null;
        }
    }


    //------------------------DELETE--------------------------
    @Transactional
    public void delete (Long id){
        Optional<Proyecto> response = projectRepository.findById(id);
        if (response!=null){
            Proyecto projectToDelete = response.get();
            projectRepository.delete(projectToDelete);
        }
    }
    //------------------------CHANGE PROYECT STATUS--------------------------
    @Transactional
    public void changeStatus(Long id) {
        Optional<Proyecto> answer = pRepository.findById(id);
        if (answer.isPresent()) {
            Proyecto project = answer.get();

            if (project.isAltaBaja()) {
                project.setAltaBaja(false);
            } else if (!project.isAltaBaja()) {
                project.setAltaBaja(true);
            }
        }
    }
}
