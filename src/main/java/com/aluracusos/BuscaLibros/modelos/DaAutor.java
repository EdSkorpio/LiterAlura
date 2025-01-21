package com.aluracusos.BuscaLibros.modelos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "autores")
public class DaAutor {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
        private String fechaNacimiento;

        @OneToMany(fetch = FetchType.EAGER)
        private List<DaLibros> librosDelAutor = new ArrayList<>();

        public DaAutor() {
        }

        public DaAutor(DatosAutor datosAutor) {
            this.nombre = datosAutor.nombre();
            this.fechaNacimiento = datosAutor.fechaNacimiento();
        }

        public Long getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(String fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public List<DaLibros> getLibrosDelAutor() {
            return librosDelAutor;
        }

        public void setLibrosDelAutor(List<DaLibros> librosDelAutor) {
            this.librosDelAutor = librosDelAutor;
        }

    @Override
    public String toString() {
        return """
               ****************************************
               \nAutor: %s
               Fecha de Nacimiento: %s,\n
               ****************************************
               """.formatted(nombre, fechaNacimiento);
    }
}

