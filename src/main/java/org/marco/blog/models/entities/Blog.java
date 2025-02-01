package org.marco.blog.models.entities;

import java.util.Date;
import java.util.List;

import org.marco.blog.enums.Periodo;

import jakarta.validation.Valid;
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

    private Periodo periodicidad;

    @NotNull
    private int periodicidadIndex;

    private int puntuacionMinima;

    private int puntuacionMaxima;

    private Double puntuacionPromedio;

    private boolean permitirComentarios;

    private Date createdDate = new Date();

    @NotNull
    @Valid
    private Autor autor;

    @Valid
    private List<Comentario> listaComentarios;

}
