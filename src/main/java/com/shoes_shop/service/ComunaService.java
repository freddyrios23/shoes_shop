package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.ComunaDTO;
import com.shoes_shop.model.Comuna;
import com.shoes_shop.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    private ComunaDTO convertirDTO(Comuna comuna){
        ComunaDTO comunaDTO = new ComunaDTO();
        comunaDTO.setId(comuna.getId());
        comunaDTO.setNombre(comuna.getNombre());
        
        if (comuna.getRegion() != null) {
            comunaDTO.setNombreRegion(comuna.getRegion().getNombre());
        }
        return comunaDTO;
    }

    public List<ComunaDTO> obtenerTodas(){
        return comunaRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public ComunaDTO buscarPorID(Integer id){
        Comuna comuna = comunaRepository.findById(id).orElseThrow(()-> new RuntimeException("Comuna no encontrada"));
        return convertirDTO(comuna);
    }

    public Comuna guardarComuna(Comuna comuna){
        return comunaRepository.save(comuna);
    }

    public Comuna actualizarComuna (Integer id, Comuna comuna){
        Comuna comunaEditada = comunaRepository.findById(id).orElseThrow(()-> new RuntimeException("Comuna no econtrada"));
        if (comuna.getNombre()!=null) {
            comunaEditada.setNombre(comuna.getNombre());
        }
        return comunaRepository.save(comunaEditada);
    }

    public String eliminarComuna(Integer id){
        try {
            Comuna comuna = comunaRepository.findById(id).orElseThrow(()-> new RuntimeException("Comuna no econtrada"));
            comunaRepository.delete(comuna);
            return "La comuna '" + comuna.getNombre() + "' ha sido eliminada exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
