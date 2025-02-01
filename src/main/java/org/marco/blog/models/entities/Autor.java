package org.marco.blog.models.entities;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Autor {
    private Long id;

    @NotBlank
    private String nombres;

    @NotBlank
    private String paterno;

    @NotBlank
    private String materno;

    private Date fechaNacimiento;

    @NotBlank
    private String paisResidencia;

    @Email
    @NotBlank
    private String email;

    private Date createdDate;
}
