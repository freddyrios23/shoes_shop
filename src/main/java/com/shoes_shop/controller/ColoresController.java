package com.shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shoes_shop.model.Color;
import com.shoes_shop.service.ColorService;

@RestController
@RequestMapping("/api/v1/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<Color>> todosLosColores() {
        List<Color> colores = colorService.obtenerTodos();

        if (colores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(colores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> agregarColor(@RequestBody Color color) {
        try {
            return new ResponseEntity<>(colorService.guardarColor(color), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo guardar el color", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(colorService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Color no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Color color) {
        try {
            return new ResponseEntity<>(colorService.actualizarColor(id, color), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar color", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(colorService.eliminarColor(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar color", HttpStatus.BAD_REQUEST);
        }
    }
}