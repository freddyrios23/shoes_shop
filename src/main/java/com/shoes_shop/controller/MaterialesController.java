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

import com.shoes_shop.model.Materiales;
import com.shoes_shop.service.MaterialesService;

@RestController
@RequestMapping("/api/v1/materiales")
public class MaterialesController {
@Autowired
    private MaterialesService materialesService;

    @GetMapping
    public ResponseEntity<List<Materiales>> todasLasRelacionesMateriales() {
        List<Materiales> materiales = materialesService.obtenerTodos();
        if (materiales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materiales, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> agregarRelacionMaterial(@RequestBody Materiales relacion) {
        try {
            return new ResponseEntity<>(materialesService.guardarRelacion(relacion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo guardar la relación", HttpStatus.BAD_REQUEST);
        }
    }
}
