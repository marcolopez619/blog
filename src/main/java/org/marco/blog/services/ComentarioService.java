package org.marco.blog.services;

import org.marco.blog.models.entities.Comentario;

public interface ComentarioService {
    Comentario save(Comentario comentario);

    Comentario edit(Comentario comentario);

    void eliminarComentario(Long id);
}
