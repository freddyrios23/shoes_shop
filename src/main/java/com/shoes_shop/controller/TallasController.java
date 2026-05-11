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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tallas_zapatillas")
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

public ResponseEntity<?> agregarTalla(@Valid @RequestBody Tallas relacion) {
        try {
            String mensaje = tallasService.guardarRelacion(relacion);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return new ResponseEntity<>("No se pudo asignar la talla a la zapatilla", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregaraRelacion(@RequestBody Tallas relacion){
        try {
            return new ResponseEntity<>(tallasService.guardarRelacion(relacion),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("no se guardo la relacion",HttpStatus.BAD_REQUEST);
        }
    }
}
