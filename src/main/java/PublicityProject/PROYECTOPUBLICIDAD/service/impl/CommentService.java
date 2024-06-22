package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Comment;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Transactional
    public String createComment(Comment request)throws MyException {
        Validacion(request);
        try{
            Comment comment = new Comment();
            comment.setUsuario(request.getUsuario());
            comment.setFecha(request.getFecha());
            comment.setContenido(request.getContenido());
            commentRepository.save(comment);
            return "se ha guardado un comentario exitosamente en la base de datos";}
        catch (Exception e){
            throw new MyException("hubo un error: "+e.getMessage());
        }
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Transactional
    public String updateComment(Long id, Comment request) throws MyException {
        Optional<Comment> op= commentRepository.findById(id);
        if (op.isPresent()){
            Comment comment=commentRepository.getById(id);
            comment.setContenido(request.getContenido());
            comment.setFecha(request.getFecha());
            comment.setUsuario(request.getUsuario());
            commentRepository.save(comment);
            return "se han guardado exitosamente los cambios en la base de datos";}
        else {throw new MyException("el id no corresponde a ningún comentario");}

    }
    @Transactional
    public String deleteComment(Long id) throws MyException{
        try {
            Optional<Comment>comentario=commentRepository.findById(id);
            if (comentario.isPresent()){
                Comment comment = commentRepository.getById(id);
                commentRepository.delete(comment);
                return "se ha eliminado de la base de datos el comentario exitosamente";}
            else {throw new MyException("error, el comentario no corresponde al id");}
        }catch (Exception e){
            throw new MyException(e.getMessage());
        }

    }
    public void Validacion(Comment request) throws MyException {
        if (request.getContenido() == null || request.getContenido().isEmpty()) {
            throw new MyException("el contenido es nulo o esta vacio");
        }
        if (request.getUsuario() == null) {
            throw new MyException("el usuario es nulo o es inexistente");
        }
        if (request.getFecha() == null) {
            throw new MyException("la fecha es nulo o es inexistente");
        }
    }
}