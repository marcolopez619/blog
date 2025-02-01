package org.marco.blog.controllers;

import org.marco.blog.services.AutorService;
import org.marco.blog.services.BlogService;
import org.marco.blog.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    // @Autowired
    // private AutorService autorService;

    // @Autowired
    // private ComentarioService comentarioService;

    @Autowired
    private BlogService blogService;

    @GetMapping()
    public ResponseEntity<?> getBlogList() {
        var blogList = blogService.getBlogList();

        if (blogList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(blogList);
    }

}
