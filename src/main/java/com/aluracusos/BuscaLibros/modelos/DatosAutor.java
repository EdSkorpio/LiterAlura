package com.aluracusos.BuscaLibros.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)

    public record DatosAutor(
            @JsonAlias("name") String nombre,
            @JsonAlias("birth_year") String fechaNacimiento
    ) {
    }
