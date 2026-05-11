package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.TallaDTO;
import com.shoes_shop.model.Talla;
import com.shoes_shop.repository.TallaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TallaService {
    @Autowired
    private TallaRepository tallaRepository;

    private TallaDTO convertirDTO (Talla talla){
        TallaDTO tallaDTO = new TallaDTO();
        tallaDTO.setId(talla.getId());
        tallaDTO.setNumero(talla.getNumero());
        return tallaDTO;
    }

    public List<TallaDTO> obtenerTodas(){
        return tallaRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public TallaDTO buscarPorId(Integer id){
        Talla talla=tallaRepository.findById(id).orElseThrow(()-> new RuntimeException("Talla no econtrada"));
        return convertirDTO(talla);
    }

    public Talla guardarTalla(Talla talla){
        return tallaRepository.save(talla);
    }

    public Talla actualizarTalla (Integer id,Talla talla){
        Talla tal = tallaRepository.findById(id).orElseThrow(()-> new RuntimeException("Talla no encontrada en los registros"));
        if (talla.getNumero()!=null) {
            tal.setNumero(talla.getNumero());
        }
        return tallaRepository.save(tal);
    }

    public String eliminarTalla(Integer id){
        try {
            Talla talla = tallaRepository.findById(id).orElseThrow(()-> new RuntimeException("¿Imposible eliminar! Talla con el id" + id + "no existe"));
            tallaRepository.delete(talla);
            return "La Talla '" + talla.getId() + "' ha sido eliminada exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
