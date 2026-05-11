package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.BoletaDTO;
import com.shoes_shop.DTO.MarcaDTO;
import com.shoes_shop.DTO.MaterialDTO;
import com.shoes_shop.model.Boleta;
import com.shoes_shop.model.Boletas;
import com.shoes_shop.model.Marca;
import com.shoes_shop.model.Material;
import com.shoes_shop.repository.BoletaRepository;
import com.shoes_shop.repository.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    private MaterialDTO convertirDTO (Material material){
        MaterialDTO materialDto = new MaterialDTO();
        materialDto.setId(material.getId());
        materialDto.setNombre(material.getNombre());
        return materialDto;
    }
    public List<MaterialDTO> obtenerTodos(){
        return materialRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public MaterialDTO buscarPorId(Integer id){
        Material material=materialRepository.findById(id).orElseThrow(()-> new RuntimeException("Material no econtrado"));
        return convertirDTO(material);
    }

    public Material guardarMaterial(Material material){
        return materialRepository.save(material);
    }

    public Material actualizarMaterial (Integer id,Material material){
        Material mater = materialRepository.findById(id).orElseThrow(()-> new RuntimeException("Material no encontrada en los registros"));
        if (material.getNombre()!=null) {
            mater.setNombre(material.getNombre());
        }
        return materialRepository.save(mater);
    }

    public String eliminarMaterial(Integer id){
        try {
            Material material = materialRepository.findById(id).orElseThrow(()-> new RuntimeException("¿Imposible eliminar! Material con el id" + id + "no existe"));
            materialRepository.delete(material);
            return "el material '" + material.getId() + "' ha sido eliminado exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
