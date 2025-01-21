package com.aluracusos.BuscaLibros.repository;

import com.aluracusos.BuscaLibros.modelos.DaAutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositAutor extends JpaRepository<DaAutor, Long> {
    Optional<DaAutor> findByNombre(String nombre);
    List<DaAutor> findByNombreContainingIgnoreCase(String nombreAutor);
}
