package org.marco.blog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.marco.blog.enums.Periodo;
import org.marco.blog.models.entities.Autor;
import org.marco.blog.models.entities.Blog;
import org.marco.blog.models.entities.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private AutorService autorService;

    @Autowired
    private ComentarioService comentarioService;

    public static Long blogId = 1L;
    public static List<Blog> blogList = new ArrayList<Blog>();

    @Override
    public Blog save(Blog blog) {
        blog.setId(blogId++);
        Autor newAutor = autorService.save(blog.getAutor());
        Comentario newComentario = comentarioService.save(blog.getListaComentarios().getFirst(), blog.getId());
        blog.setAutor(newAutor);
        blog.setCreatedDate(new Date());
        blog.setListaComentarios(new ArrayList<Comentario>() {
            {
                add(newComentario);
            }
        });
        Periodo periodicidad = Periodo.values()[blog.getPeriodicidadIndex()];
        blog.setPeriodicidad(periodicidad);
        blogList.add(blog);
        return blog;
    }

    @Override
    public Blog update(Blog blog) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        return blogList.stream().filter(blog -> blog.getId() == id).findFirst();
    }

    @Override
    public List<Blog> getBlogList() {
        return blogList;
    }

    @Override
    public Blog addComment(Comentario comentario, long blogId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addComment'");
    }

}
