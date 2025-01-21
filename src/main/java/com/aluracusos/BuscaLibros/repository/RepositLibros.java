package com.aluracusos.BuscaLibros.repository;

import com.aluracusos.BuscaLibros.modelos.DaLibros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface RepositLibros extends JpaRepository<DaLibros, Long> {
        List<DaLibros> findTop10ByOrderByNumeroDeDescargasDesc();
        List<DaLibros>  findAll();

    }

