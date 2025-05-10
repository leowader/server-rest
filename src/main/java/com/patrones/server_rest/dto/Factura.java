package com.patrones.server_rest.dto;

import com.patrones.server_rest.domain.Emisor;
import com.patrones.server_rest.domain.Producto;
import com.patrones.server_rest.domain.Receptor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    private Emisor emisor;
    private Receptor receptor;
    private LocalDate fechaEmision;
    private  String numeroFactura;
    private List<Producto> productos;
    private double total;

}
