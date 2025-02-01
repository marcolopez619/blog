package org.marco.blog.controllers;

import org.marco.blog.models.entities.Autor;
import org.marco.blog.models.entities.Blog;
import org.marco.blog.models.entities.Comentario;
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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Operation(summary = "Obtiene la lista de blogs con todos sus atributos")
    @GetMapping()
    public ResponseEntity<?> getBlogList() {
        var blogList = blogService.getBlogList();

        if (blogList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(blogList);
    }

    @Operation(summary = "Obtiene la informacion de un blog por su ID")
    @GetMapping("/by-id")
    public ResponseEntity<?> getBlogById(@Valid @RequestParam Long id) {
        var optionalBlog = blogService.getBlogById(id);

        if (optionalBlog.isPresent()) {
            return ResponseEntity.ok(optionalBlog.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Permite guardar la informacion de un blog")
    @PostMapping
    public ResponseEntity<?> saveBlog(@Valid @RequestBody Blog blog, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Utils.getErrors(bindingResult);
        }

        Blog newBlog = blogService.save(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBlog);
    }

    @Operation(summary = "Permite anadir un comentario a un Blog")
    @PostMapping(value = "/add-comment")
    public ResponseEntity<?> addComment(@Valid @RequestBody Comentario comentario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Utils.getErrors(bindingResult);
        }

        var blogOptional = blogService.addComment(comentario);

        if (blogOptional.isPresent()) {

            if (!blogOptional.get().isPermitirComentarios()) {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                        .body("EL BLOG NO PERMITE REGISTRAR COMENTARIOS");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(blogOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Permite guardar la informacion de un autor")
    @PostMapping(value = "/save-autor")
    public ResponseEntity<?> saveNewAutor(@Valid @RequestBody Autor autor, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Utils.getErrors(bindingResult);
        }

        var newAutor = blogService.saveNewAutor(autor);

        return ResponseEntity.status(HttpStatus.CREATED).body(newAutor);
    }

    @Operation(summary = "Permite actualizar la informacion de un blog")
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
