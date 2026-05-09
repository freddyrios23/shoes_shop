package com.shoes_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoes_shop.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
