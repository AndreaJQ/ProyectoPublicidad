package PublicityProject.PROYECTOPUBLICIDAD.controller;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Comment;
import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import PublicityProject.PROYECTOPUBLICIDAD.exceptions.MyException;
import PublicityProject.PROYECTOPUBLICIDAD.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Create
    @PostMapping(value="/CrearComentario")
    public String createComment(@RequestBody Comment request) throws MyException {
        commentService.createComment(request);
        return "Comentario Creado exitosamente";
    }



    @GetMapping(value = "/listaComentarios")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // Update
    @PutMapping("/modificarComentario/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody Comment request)throws MyException {
        commentService.updateComment(id, request);
        return "se ha modificado exitosamente el comentario";
    }


    @DeleteMapping("/borrarComentario/{id}")
    public String deleteComment(@PathVariable Long id) throws MyException{
        commentService.deleteComment(id);
        return "el comentario se ha eliminado exitosamente";
    }


}