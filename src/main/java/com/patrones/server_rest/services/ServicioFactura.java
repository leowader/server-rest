package com.patrones.server_rest.services;

import com.patrones.server_rest.domain.adaptador.AdaptadorFacturaA;
import com.patrones.server_rest.domain.adaptador.FacturaJson;
import com.patrones.server_rest.domain.Factura;
import com.patrones.server_rest.domain.adaptador.FacturaJson;
import org.springframework.stereotype.Service;

@Service
public class ServicioFactura {

    FacturaJson facturaJsonA;

    public ServicioFactura() {
        this.facturaJsonA = new FacturaJson();
    }

    public FacturaJson generarFactura(Factura factura) {
        AdaptadorFacturaA adaptadorFacturaA = new AdaptadorFacturaA(facturaJsonA);
        return adaptadorFacturaA.procesarFactura(factura);

    }
}
