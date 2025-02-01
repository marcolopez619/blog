package org.marco.blog.models.entities;

import java.util.Date;
import java.util.List;

import org.marco.blog.enums.Periodo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Blog {

    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String tema;

    @NotBlank
    private String contenido;

    @NotNull
    private Periodo periodicidad;

    @NotNull
    private Autor autor;

    private boolean permitirComentarios = true;

    private List<Comentario> listaComentarios;

    private Date createdDate;

}
