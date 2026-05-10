package com.shoes_shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shoes_shop.model.Color;

public interface ColorRepository extends JpaRepository<Color,Integer>{

}
