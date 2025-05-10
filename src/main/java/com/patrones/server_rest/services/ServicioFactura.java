package com.patrones.server_rest.services;

import com.patrones.server_rest.dto.Factura;
import com.patrones.server_rest.domain.adapter.AdaptadorFactura;
import com.patrones.server_rest.domain.adapter.FacturaJson;
import com.patrones.server_rest.dto.FacturaResponse;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class ServicioFactura {

    public FacturaJson generarFactura(Factura factura) {
        FacturaJson facturaJson = new FacturaJson();
        AdaptadorFactura adaptadorFactura = new AdaptadorFactura(facturaJson);
        FacturaJson facturaJsonResponse= adaptadorFactura.convertirXml(factura);
        System.out.println("FACTURAAA LISTA EN JSON"+facturaJsonResponse.getFirmaDigital());
        return facturaJsonResponse;

    }
}
