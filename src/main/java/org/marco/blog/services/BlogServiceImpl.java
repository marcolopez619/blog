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
    public Optional<Blog> update(Blog blog) {

        var optionalBlog = getBlogById(blog.getId());

        if (!optionalBlog.isPresent()) {
            return optionalBlog;
        }

        int index = blogList.indexOf(blogList.stream().filter(x -> x.getId() == blog.getId()).findFirst().get());

        var blogfounded = optionalBlog.get();

        blogfounded.setTitulo(blog.getTitulo());
        blogfounded.setTema(blog.getTema());
        blogfounded.setContenido(blog.getContenido());
        blogfounded.setPeriodicidad(Periodo.values()[blog.getPeriodicidadIndex()]);

        blog.getAutor().setId(blogfounded.getAutor().getId());
        ;
        blog.getAutor().setCreatedDate(blogfounded.getAutor().getCreatedDate());
        blogfounded.setAutor(blog.getAutor());

        blogfounded.setPermitirComentarios(blog.isPermitirComentarios());

        blog.getListaComentarios().getFirst().setId(blogfounded.getListaComentarios().getFirst().getId());
        blog.getListaComentarios().getFirst().setBlogId(blogfounded.getListaComentarios().getFirst().getBlogId());
        blog.getListaComentarios().getFirst()
                .setCreatedDate(blogfounded.getListaComentarios().getFirst().getCreatedDate());
        blogfounded.setListaComentarios(blog.getListaComentarios());

        blogList.set(index, blogfounded);

        return Optional.of(blogfounded);
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
    public Optional<Blog> addComment(Comentario comentario) {

        var blogOptional = getBlogById(comentario.getBlogId());

        if (!blogOptional.isPresent()) {
            return blogOptional;
        }

        var comentarioFormated = comentarioService.save(comentario, comentario.getBlogId());

        blogOptional.get().getListaComentarios().add(comentarioFormated);

        return blogOptional;
    }

}
