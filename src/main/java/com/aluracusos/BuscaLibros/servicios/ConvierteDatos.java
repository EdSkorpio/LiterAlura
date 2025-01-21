package com.aluracusos.BuscaLibros.servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper mapeo = new ObjectMapper();


    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return mapeo.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}