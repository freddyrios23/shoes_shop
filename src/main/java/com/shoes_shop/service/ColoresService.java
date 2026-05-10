package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.model.Colores;
import com.shoes_shop.repository.ColoresRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColoresService {

    @Autowired
    private ColoresRepository coloresRepository;

    public List<Colores> obtenerTodos() {
        return coloresRepository.findAll();
    }

    public String guardarRelacion(Colores relacion) {
        coloresRepository.save(relacion);

        return "La zapatilla " + relacion.getZapatilla().getNombre() + " fue asignada al color " + relacion.getColor().getColor();
    }
}