package org.marco.blog.services;

import java.util.List;
import java.util.Optional;

import org.marco.blog.models.entities.Autor;
import org.marco.blog.models.entities.Blog;
import org.marco.blog.models.entities.Comentario;

public interface BlogService {

    Blog save(Blog blog);

    Optional<Blog> update(Blog blog);

    Optional<Blog> getBlogById(Long id);

    List<Blog> getBlogList();

    Optional<Blog> addComment(Comentario comentario);

    Autor saveNewAutor(Autor autor);
}
