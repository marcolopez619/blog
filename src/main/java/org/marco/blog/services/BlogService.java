package org.marco.blog.services;

import java.util.List;

import org.marco.blog.models.entities.Blog;
import org.marco.blog.models.entities.Comentario;

public interface BlogService {

    Blog save(Blog blog);

    Blog update(Blog blog);

    Blog getBlog(Long id);

    List<Blog> getBlogList();

    Blog addComment(Comentario comentario, long blogId);
}
