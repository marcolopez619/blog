package org.marco.blog.utils;

import java.util.HashMap;
import java.util.List;

import org.marco.blog.models.entities.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class Utils {

    public static ResponseEntity<?> getErrors(BindingResult bindingResult) {
        var errors = new HashMap<String, String>();

        bindingResult.getFieldErrors().forEach(error -> {
            errors.put(error.getField(),
                    "Error on the field '" + error.getField() + "' : " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    public static void setStadisticsValues(List<Blog> blogList) {

        blogList.stream().forEach(blog -> {
            var promedio = blog.getListaComentarios().stream().mapToInt(x -> x.getPuntuacion())
                    .average().orElse(0);

            var minimo = blog.getListaComentarios().stream().mapToInt(x -> x.getPuntuacion()).min().orElse(0);
            var maximo = blog.getListaComentarios().stream().mapToInt(x -> x.getPuntuacion()).max().orElse(0);

            blog.setPuntuacionPromedio(promedio);
            blog.setPuntuacionMaxima(maximo);
            blog.setPuntuacionMinima(minimo);
        });
    }

}