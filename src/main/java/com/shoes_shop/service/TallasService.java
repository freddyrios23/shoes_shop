package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shoes_shop.model.Tallas;
import com.shoes_shop.repository.TallasRepository;

public class TallasService {
    @Autowired
    private TallasRepository tallasRepository;

    public List<Tallas> obtenerTodos() {
        return tallasRepository.findAll();
    }

    public String guardarRelacion(Tallas relacion) {
    tallasRepository.save(relacion);
        return "La zapatilla " + relacion.getZapatilla().getNombre() + " fue asignada a la talla " + relacion.getTalla().getNumero();
    }
}
