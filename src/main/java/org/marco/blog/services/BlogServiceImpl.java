package org.marco.blog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.marco.blog.models.entities.Blog;
import org.marco.blog.models.entities.Comentario;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    public static Long blogId = 1L;

    public static List<Blog> blogList = new ArrayList<Blog>();

    @Override
    public Blog save(Blog blog) {
        blog.setId(blogId);
        blog.setCreatedDate(new Date());
        blogList.add(blog);
        return blog;
    }

    @Override
    public Blog update(Blog blog) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Blog getBlog(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBlog'");
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
