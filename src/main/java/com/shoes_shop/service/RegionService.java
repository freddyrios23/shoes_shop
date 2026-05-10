package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.RegionDTO;
import com.shoes_shop.model.Region;
import com.shoes_shop.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    private RegionDTO convertirDTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        dto.setNombre(region.getNombre());
        return dto;
    }

    public List<RegionDTO> obtenerTodas() {
        return regionRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public RegionDTO buscarPorId(Integer id) {
        Region region = regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Región no encontrada"));
        return convertirDTO(region);
    }


    public Region guardarRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region actualizarRegion(Integer id, Region region) {
        Region regionEditada = regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Región no encontrada"));

        if (region.getNombre() != null) {
            regionEditada.setNombre(region.getNombre());
        }

        return regionRepository.save(regionEditada);
    }

    public String eliminarRegion(Integer id) {
        try {
            Region region = regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Región no encontrada"));

            regionRepository.delete(region);

            return "La región '" + region.getNombre() + "' fue eliminada correctamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}