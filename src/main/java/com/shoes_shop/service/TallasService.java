package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.shoes_shop.model.Tallas;
import com.shoes_shop.repository.TallasRepository;


import org.springframework.stereotype.Service;

import com.shoes_shop.model.Boletas;
import com.shoes_shop.model.Tallas;
import com.shoes_shop.repository.BoletasRepository;
import com.shoes_shop.repository.TallasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class TallasService {
    @Autowired
    private TallasRepository tallasRepository;

    public List<Tallas> obtenerTodos() {
        return tallasRepository.findAll();
    }

    public String guardarRelacion(Tallas tallas) {
    tallasRepository.save(tallas);
        return "La zapatilla " + tallas.getZapatilla().getNombre() + " fue asignada a la talla " + tallas.getTalla().getNumero();
    }
}

