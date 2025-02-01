package org.marco.blog.utils;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class Utils {

    public static ResponseEntity<?> getErrors(BindingResult bindingResult) {
        var errors = new HashMap<String, String>();

        bindingResult.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "Error on : " + error.getField() + " : " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

}