package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.model.Tipos;
import com.shoes_shop.repository.TiposRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class TiposService {
    @Autowired
    private TiposRepository tiposRepository;

    public List<Tipos> obtenerTodas(){
        return tiposRepository.findAll();
    }

    public String guardarRelacion(Tipos relacion){
        tiposRepository.save(relacion);
        return "El tipo de zapatilla" + relacion.getTipo().getId() + "El tipo de zapatilla fue agregado" + relacion.getTipo().getId();
    }
}
