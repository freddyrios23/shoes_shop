package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.model.Boletas;
import com.shoes_shop.repository.BoletasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletasService {

    @Autowired
    private BoletasRepository boletasRepository;

    public List<Boletas> obtenerTodas(){
        return boletasRepository.findAll();
    }

    public String guardarRelacion(Boletas relacion){
        boletasRepository.save(relacion);
        return "La zapatilla" + relacion.getZapatilla().getNombre() + "fue agregada a la boleta" + relacion.getBoleta().getId();
    }   
}
