package com.shoes_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoes_shop.model.Boletas;

public interface BoletasRepository extends JpaRepository<Boletas,Integer> {
    
}
