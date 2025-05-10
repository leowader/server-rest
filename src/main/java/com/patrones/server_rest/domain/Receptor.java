package com.patrones.server_rest.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Receptor {
    private String nombre;
    private String nit;
}
