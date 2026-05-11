package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.MetodoEnvioDTO;
import com.shoes_shop.model.MetodoEnvio;
import com.shoes_shop.repository.MetodoEnvioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoEnvioService {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    private MetodoEnvioDTO convertirADTO(MetodoEnvio metodoEnvio) {

        MetodoEnvioDTO metodoEnvioDto = new MetodoEnvioDTO();

        metodoEnvioDto.setId(metodoEnvio.getId());
        metodoEnvioDto.setTipo(metodoEnvio.getTipo());
        metodoEnvioDto.setCosto(metodoEnvio.getCosto());
        metodoEnvioDto.setTiempoEntrega(metodoEnvio.getTiempoEntrega());

        return metodoEnvioDto;
    }

    public List<MetodoEnvioDTO> obtenerTodas(){
        return metodoEnvioRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public MetodoEnvioDTO buscarPorId(Integer id){
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id).orElseThrow(() -> new RuntimeException("¡envio no encontrado!"));
        return convertirADTO(metodoEnvio);
    } 

    public MetodoEnvio guardarMetodoEnvio (MetodoEnvio metodoEnvio){
        return metodoEnvioRepository.save(metodoEnvio);
    }

    public MetodoEnvio actualizarMetodoEnvio(Integer id,MetodoEnvio metodoEnvio){
        MetodoEnvio metodoEnvio2 = metodoEnvioRepository.findById(id).orElseThrow(()-> new RuntimeException("¡el envio no existe en los registros"));
        if (metodoEnvio.getTipo()!= null) {
            metodoEnvio2.setTipo(metodoEnvio.getTipo());
        }
        if (metodoEnvio.getCosto()!= null) {
            metodoEnvio2.setCosto(metodoEnvio.getCosto());
        }
        if (metodoEnvio.getTiempoEntrega()!= null) {
            metodoEnvio2.setTiempoEntrega(metodoEnvio.getTiempoEntrega());
        }
        return metodoEnvioRepository.save(metodoEnvio2);
    }

    public String eliminarMetodoEnvio(Integer id){
        try {
            MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id).orElseThrow(()-> new RuntimeException("¿Imposible eliminar! el envio  con el " + id + "no existe"));
            metodoEnvioRepository.delete(metodoEnvio);
            return "EL envio '" + metodoEnvio.getId() + "' ha sido eliminada exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
