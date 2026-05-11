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

import com.shoes_shop.model.Tallas;
import com.shoes_shop.service.TallasService;

@RestController
@RequestMapping("/api/tallas")
public class TallasController {
    @Autowired
    private TallasService tallasService;

    @GetMapping
    public ResponseEntity<List<Tallas>> todasLasTallas() {
        List<Tallas> lista = tallasService.obtenerTodos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> agregarTalla(@RequestBody Tallas tallas) {
        try {
            String mensaje = tallasService.guardarRelacion(tallas);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo asignar la talla a la zapatilla", HttpStatus.BAD_REQUEST);
        }
    }
}
