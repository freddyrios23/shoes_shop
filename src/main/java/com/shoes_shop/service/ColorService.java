package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.ColorDTO;
import com.shoes_shop.model.Color;
import com.shoes_shop.repository.ColorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColorService {
@Autowired
    private ColorRepository colorRepository;

    private ColorDTO convertirDTO (Color  color){
        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setId(color.getId());
        colorDTO.setColor(color.getColor());
        return colorDTO;
    }

    public List<ColorDTO> obtenerTodos(){
        return colorRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public ColorDTO buscarPorId(Integer id){
        Color color=colorRepository.findById(id).orElseThrow(()-> new RuntimeException("color no encontrado"));
        return convertirDTO(color);
    }

    public Color guardarColor(Color color){
        return colorRepository.save(color);
    }

    public Color actualizarColor (Integer id,Color color){
        Color colr = colorRepository.findById(id).orElseThrow(()-> new RuntimeException("Color no encontrado en los registros"));
        if (color.getColor()!=null) {
            color.setColor(color.getColor());
        }
        return colorRepository.save(colr);
    }

    public String eliminarColor(Integer id){
        try {
            Color color = colorRepository.findById(id).orElseThrow(()-> new RuntimeException("¿Imposible eliminar! Marca con el id" + id + "no existe"));
            colorRepository.delete(color);
            return "el color '" + color.getId() + "' ha sido eliminado exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
