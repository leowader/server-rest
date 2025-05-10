package com.patrones.server_rest.dto;

import com.patrones.server_rest.domain.Factura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaResponse extends Factura {
    private String firmaDigital;


}
