package PublicityProject.PROYECTOPUBLICIDAD.service.impl;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Comment;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(String contenido, UserEntity usuario) {
        Comment comment = new Comment();
        comment.setContenido(contenido);
        comment.setUsuario(usuario);
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment updateComment(String id, String contenido, UserEntity usuario) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContenido(contenido);
        comment.setUsuario(usuario);
        return commentRepository.save(comment);
    }

    public void deleteComment(String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }
}