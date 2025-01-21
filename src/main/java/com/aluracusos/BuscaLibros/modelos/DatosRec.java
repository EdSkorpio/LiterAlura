package com.aluracusos.BuscaLibros.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosRec(
        @JsonAlias("results") List<DatosLibros> listaLibros
) {
}
