package com.shoes_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tallas_zapatillas")
public class Tallas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "zapatilla_id")
    private Zapatilla zapatilla;

    @ManyToOne
    @JoinColumn(name = "talla_id")
    private Tallas talla;

    @ManyToOne
    @JoinColumn(name = "numero")
    private Tallas numero;
}
