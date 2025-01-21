package com.aluracusos.BuscaLibros.modelos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Entity
@Table(name = "libros")
public class DaLibros {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true)
        private String titulo;
        @Column(name = "idiomas")
        private String idiomas;
        private Double numeroDeDescargas;
        private String detallesLibro;
        @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
        private List<DaAutor> autores = new ArrayList<>();
        public DaLibros() {
        }

        public DaLibros(DatosLibros datosLibros) {
            this.titulo = datosLibros.titulo();
            this.detallesLibro = String.join(",",datosLibros.detallesLibro());
            this.idiomas = String.join(",", datosLibros.idiomas());
            this.numeroDeDescargas = datosLibros.numeroDescargas();
        }
        public Long getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public List<String> getIdiomas() {
            return Arrays.asList(idiomas.split(","));
        }

        public void setIdiomas(List<String> idiomas) {
            this.idiomas = String.join(",", idiomas);
        }

        public Double getNumeroDeDescargas() {
            return numeroDeDescargas;
        }

        public void setNumeroDeDescargas(Double numeroDeDescargas) {
            this.numeroDeDescargas = numeroDeDescargas;
        }

        public List<DaAutor> getAutores() {
            return autores;
        }

        public void setAutores(List<DaAutor> autores) {
            this.autores = autores;
        }

    public String getDetallesLibro() {
        return detallesLibro;
    }

    public void setDetallesLibro(String detallesLibro) {
        this.detallesLibro = detallesLibro;
    }

    @Override
        public String toString() {
            return """
                
                \n*****************************************
                         El LIBRO ENCONTRADO ES:
                *****************************************
                \nTitulo: %s\n%s
                Idioma: %s
                Descargas Totales: %f
                \n*****************************************"""
                    .formatted(titulo, autores, idiomas, numeroDeDescargas);
        }
    }

