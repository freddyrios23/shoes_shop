package com.shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shoes_shop.model.Colores;
import com.shoes_shop.service.ColoresService;

@RestController
@RequestMapping("/api/v1/colores")
public class ColoresController {

    @Autowired
    private ColoresService coloresService;

    @GetMapping
    public ResponseEntity<List<Colores>> todosLosColores() {
        List<Colores> colores = coloresService.obtenerTodos();

        if (colores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(colores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> agregarColores(@RequestBody Colores colores) {
        return new ResponseEntity<>(
                coloresService.guardarRelacion(colores),
                HttpStatus.CREATED
        );

    }

}