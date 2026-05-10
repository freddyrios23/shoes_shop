package com.shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoes_shop.model.Colores;
import com.shoes_shop.service.ColoresService;

@RestController
@RequestMapping("/api/v1/colores_zapatillas")
public class ColoresController {

    @Autowired
    private ColoresService coloresService;

    @GetMapping
    public ResponseEntity<List<Colores>> todosLosColoresZapatillas() {
        List<Colores> colores = coloresService.obtenerTodos();

        if (colores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(colores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> agregarRelacion(@RequestBody Colores relacion) {
        try {
            return new ResponseEntity<>(coloresService.guardarRelacion(relacion),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("no se guardo la relacion",HttpStatus.BAD_REQUEST);
        }
    }
}