package com.patrones.server_rest.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Emisor {
    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;

}
