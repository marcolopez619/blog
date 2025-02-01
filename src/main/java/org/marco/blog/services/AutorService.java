package org.marco.blog.services;

import java.util.List;
import java.util.Optional;

import org.marco.blog.models.entities.Autor;

public interface AutorService {
    List<Autor> getListAutors();

    Optional<Autor> getById(Long id);

    Autor save(Autor autor);
}
