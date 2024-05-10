package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Image;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.Role;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;

    //--------------------CREATE--------------------------
    @Transactional
    public void create(UserEntity us, String password, String password2, MultipartFile archivo) throws MyException, IOException {
        //validate(us,password,password2,archivo);
        Image image;
        if (archivo != null && !archivo.isEmpty()) {
            image = imageService.createImagen(archivo);
        } else {
            image = imageService.getDefaultImage();
        }
        us.setImage(image);
        us.setStatus(false);
        us.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(us);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserEntity> list() {
        List<UserEntity> user = new ArrayList<>();
        user = userRepository.findAll();
        return user;
    }

    //---------------------GET USER BY ID-----------------
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //---------------------UPDATE USER-----------------

    public UserEntity updateUser(Long id, String name, String lastName, String contact, String address, MultipartFile archivo) throws Exception {
        UserEntity user = new UserEntity();
        Optional<UserEntity> answer = userRepository.findById(id);
        if (answer.isPresent()) {
            user = answer.get();
            user.setName(name);
            user.setLastName(lastName);
            user.setContact(contact);
            user.setAddress(address);


            String idImage = null;

            if (user.getImage() != null) {
                idImage = user.getImage().getId();
            }
            Image image;
            if (archivo != null && !archivo.isEmpty()) {
                image = imageService.UpdateImage(archivo, idImage);
            } else {
                image = imageService.getDefaultImage();
            }


            user.setImage(image);

            userRepository.save(user);
        }
        return user;
    }


    //-----------------VALIDATE--------------------------
    /*
    private void validate(UserEntity us, String password,String password2, MultipartFile archivo) throws MyException {

        // Verificar que el atributo "name" no sea nulo o vacío
        if (us.getName() == null || us.getName().isEmpty()) {
            throw new MyException("El campo 'NOMBRE' no puede quedar vacío");
        }

        // El atributo "lastName" no se valida ya que no tiene restricciones
        // Verificar que el atributo "contact" no sea nulo o vacío
        if (us.getContact() == null || us.getContact().isEmpty()) {
            throw new MyException("El campo 'CONTACTO' no puede quedar vacío");
        }

        // Verificar que el atributo "address" no sea nulo o vacío
        if (us.getAddress() == null || us.getAddress().isEmpty()) {
            throw new MyException("El campo 'DIRECCION' no puede quedar vacío");
        }

        // Verificar que el atributo "email" no sea nulo o vacío y sea un correo electrónico válido
        if (us.getEmail() == null || us.getEmail().isEmpty()) {
            throw new MyException("El campo 'CORREO ELECTRÓNICO' no puede ser nulo, vacío o inválido");
        }

        // Verificar que el atributo "password" no sea nulo o vacío
        if (password == null || password.isEmpty()) {
            throw new MyException("El campo 'CONTRASEÑA' no puede quedar vacío");
        }
        if (!password.equals(password2)) {
            throw new MyException("Las contraseñas ingresadas deben ser iguales");
        }
        if (password.length() <= 8) {
            throw new MyException("La contraseña debe tener mínimo 8 caracteres");
        }

        // Verificar que el correo electrónico no esté registrado previamente
        if (userRepository.existsByEmail(us.getEmail())) {
            throw new MyException("El correo electrónico ya está registrado");
        }
    }*/


    @Transactional
    public void changeRole(Long id) {
        Optional<UserEntity> answer = userRepository.findById(id);
        if (answer.isPresent()) {
            UserEntity user = answer.get();

            if (user.getRole().equals(Role.USERAGENT)) {
                user.setRole(Role.ADMIN);
            } else if (user.getRole().equals(Role.ADMIN)) {
                user.setRole(Role.CLIENT);
            } else if (user.getRole().equals(Role.CLIENT)) {
                user.setRole(Role.USERAGENT);
            }

        }
    }

    @Transactional
    public void changeUserStatus(Long id) {
        Optional<UserEntity> answer = userRepository.findById(id);
        if (answer.isPresent()) {
            UserEntity user = answer.get();

            if (user.isStatus()) {
                user.setStatus(false);
            } else if (!user.isStatus()) {
                user.setStatus(true);
            }
        }
    }

    @Transactional(readOnly = true)
    public UserEntity getOne(Long id) {
        return userRepository.getOne(id);
    }


}