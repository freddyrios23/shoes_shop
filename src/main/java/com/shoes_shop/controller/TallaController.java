package com.shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoes_shop.DTO.TallaDTO;
import com.shoes_shop.model.Talla;
import com.shoes_shop.service.TallaService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v1/talla")
public class TallaController {
    @Autowired
    private TallaService tallaService;

    @GetMapping
    public ResponseEntity<List<TallaDTO>> todasLasTallas(){
        List<TallaDTO> talla = tallaService.obtenerTodas();
        if (talla.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(talla,HttpStatus.OK);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTallaPorId(@PathVariable Integer id){
        try {
            TallaDTO talla = tallaService.buscarPorId(id);
            return new ResponseEntity<>(talla,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("no se encontro la marca",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregartalla (@Valid @RequestBody Talla talla){
        try {
            return new ResponseEntity<>(tallaService.guardarTalla(talla),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("no se guardo la talla",HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editarMarca(@PathVariable Integer id,@Valid @RequestBody Talla talla){
        try {
            Talla editada = tallaService.actualizarTalla(id, talla);
            return new ResponseEntity<>(editada,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("talla no encontrada",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTalla (@PathVariable Integer id){
        String resultado = tallaService.eliminarTalla(id);

        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(resultado,HttpStatus.NOT_FOUND);
        }
    }
}
