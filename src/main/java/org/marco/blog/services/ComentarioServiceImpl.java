package org.marco.blog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.marco.blog.models.entities.Comentario;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    public static Long comentarioId = 1L;

    public static List<Comentario> comentarioList = new ArrayList<>() {
        {
            add(new Comentario(comentarioId, "nombre comentario", "coment@email.com", "ECUADOR", 5, 1, new Date()));
        }
    };

    @Override
    public Comentario save(Comentario comentario, Long idBlog) {
        comentario.setId(comentarioId++);
        comentario.setBlogId(idBlog);
        comentario.setCreatedDate(new Date());
        comentarioList.add(comentario);
        return comentario;
    }

    @Override
    public Comentario edit(Comentario comentario) {

        var commentOptional = comentarioList.stream().filter(x -> x.getId() == comentario.getId()).findFirst();

        if (commentOptional.isPresent()) {
            var commentSearched = commentOptional.get();
            int indexComment = comentarioList.indexOf(commentSearched);

            commentSearched.setNombre(comentario.getNombre());
            commentSearched.setEmail(comentario.getEmail());
            commentSearched.setPaisResidencia(comentario.getPaisResidencia());
            commentSearched.setPuntuacion(comentario.getPuntuacion());

            comentarioList.set(indexComment, commentSearched);

            return commentSearched;
        }

        return null;
    }

    @Override
    public void eliminarComentario(Long id) {
        var commentDelete = comentarioList.stream().filter(x -> x.getId() == id).findFirst();
        int index = comentarioList.indexOf(commentDelete.get());
        comentarioList.remove(index);
    }

}
