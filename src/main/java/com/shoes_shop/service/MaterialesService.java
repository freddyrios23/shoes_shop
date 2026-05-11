package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.model.Materiales;
import com.shoes_shop.repository.MaterialesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialesService {
    @Autowired
    private MaterialesRepository materialesRepository;

    public List<Materiales> obtenerTodos() {
        return materialesRepository.findAll();
    }

    public String guardarRelacion(Materiales relacion) {
        materialesRepository.save(relacion);
        
        return "La zapatilla " + relacion.getZapatilla().getNombre()
                + " fue fabricada con el material " + relacion.getMaterial().getNombre();
    }

}
