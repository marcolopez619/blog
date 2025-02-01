package org.marco.blog.controllers;

import org.marco.blog.models.entities.Blog;
import org.marco.blog.services.BlogService;
import org.marco.blog.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class BlogController {

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

    @GetMapping("/by-id")
    public ResponseEntity<?> getBlogById(@Valid @RequestParam Long id) {
        var optionalBlog = blogService.getBlogById(id);

        if (optionalBlog.isPresent()) {
            return ResponseEntity.ok(optionalBlog.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> saveBlog(@Valid @RequestBody Blog blog, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Utils.getErrors(bindingResult);
        }

        Blog newBlog = blogService.save(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBlog);
    }

    @PutMapping
    public ResponseEntity<?> updateBlog(@Valid @RequestBody Blog blog, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Utils.getErrors(bindingResult);
        }

        var updatedOptionalBlog = blogService.update(blog);

        if (updatedOptionalBlog.isPresent()) {
            return ResponseEntity.ok(updatedOptionalBlog.get());
        }

        return ResponseEntity.badRequest().build();
    }

}
