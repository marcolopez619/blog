package org.marco.blog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.marco.blog.models.entities.Autor;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl implements AutorService {

    public static Long autorId = 1L;

    public static List<Autor> autorList = new ArrayList<>() {
        {
            add(new Autor(autorId, "Marco", "Molina", "Lopez", new Date(), "Bolivia", "my_email.com", new Date()));
        }
    };

    @Override
    public List<Autor> getListAutors() {
        return autorList;
    }

    @Override
    public Optional<Autor> getById(Long id) {
        return autorList.stream().filter(x -> x.getId() == id).findFirst();// .collect(Collectors.toList()).getFirst();
    }

    @Override
    public Autor save(Autor autor) {
        autor.setId(autorId++);
        autor.setCreatedDate(new Date());
        autorList.add(autor);
        return autor;
    }

}
