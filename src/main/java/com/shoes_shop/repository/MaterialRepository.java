package com.shoes_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoes_shop.model.Material;

public  interface MaterialRepository  extends JpaRepository<Material,Integer> {

}
