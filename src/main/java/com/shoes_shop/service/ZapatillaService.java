package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.ZapatillaDTO;
import com.shoes_shop.model.Zapatilla;
import com.shoes_shop.repository.ZapatillaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ZapatillaService {

    @Autowired
    private ZapatillaRepository zapatillaRepository;

    public List<ZapatillaDTO> obtenerTodas(){
        return zapatillaRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public ZapatillaDTO buscarPorId(Integer id){
        Zapatilla zapatilla = zapatillaRepository.findById(id).orElseThrow(() -> new RuntimeException("¡La Zapatilla no encontrada!"));
        return convertirADTO(zapatilla);
    }

    public Zapatilla guardarZapatilla(Zapatilla zapatilla){
        return zapatillaRepository.save(zapatilla);
    }

    public Zapatilla actualizarZapatilla(Integer id,Zapatilla zapatilla){
        Zapatilla zapato = zapatillaRepository.findById(id).orElseThrow(()-> new RuntimeException("¡La zapatillano no existe en los registros"));
        if (zapatilla.getNombre()!=null) {
            zapato.setNombre(zapatilla.getNombre());
        }
        if (zapatilla.getPrecio()!=null) {
            zapato.setPrecio(zapatilla.getPrecio());
        }
        if (zapatilla.getMarca()!= null) {
            zapato.setMarca(zapatilla.getMarca());
        }     
        return zapatillaRepository.save(zapato);
    }

    public String eliminarZapatilla(Integer id){
        try {
            Zapatilla zapatilla = zapatillaRepository.findById(id).orElseThrow(()-> new RuntimeException("¡Imposible eliminar! La zapatilla con el id" + id + "no existe"));
            zapatillaRepository.delete(zapatilla);
            return "La zapatilla '" + zapatilla.getNombre() + "' ha sido eliminada exitosamente.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private ZapatillaDTO convertirADTO (Zapatilla zapatilla){
        ZapatillaDTO zapatillaDTO = new ZapatillaDTO();
        zapatillaDTO.setId(zapatilla.getId());
        zapatillaDTO.setNombre(zapatilla.getNombre());
        zapatillaDTO.setPrecio(zapatilla.getPrecio());

        if (zapatilla.getMarca()!=null) {
            zapatillaDTO.setMarcaId(zapatilla.getMarca().getId());
            zapatillaDTO.setNombreMarca(zapatilla.getMarca().getNombre());
        }
        return zapatillaDTO;   
    }
}
