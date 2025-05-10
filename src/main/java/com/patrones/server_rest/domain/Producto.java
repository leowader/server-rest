package com.patrones.server_rest.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    private String descripcion;
    private int Cantidad;
    private double precioUnitario;
    private double totalProducto;
}
