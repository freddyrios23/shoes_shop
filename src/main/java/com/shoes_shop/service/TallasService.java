package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Tallas> obtenerTodas(){
        return tallasRepository.findAll();
    }

    public String guardarRelacion(Tallas relacion){
        tallasRepository.save(relacion);
        return "La talla" + relacion.getZapatilla().getTalla() + "fue agregada a la boleta" + relacion.getTalla().getId();
    }
}
