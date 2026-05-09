package com.shoes_shop.DTO;

import lombok.Data;

@Data
public class ZapatillaDTO {
    private Integer id;
    private String nombre;
    private Integer talla;
    private Integer precio;
    private Integer marcaId;
    private String nombreMarca;
}
