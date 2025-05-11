package com.patrones.server_rest.services;

import com.patrones.server_rest.domain.adapter.FacturaXml;
import com.patrones.server_rest.dto.Factura;
import com.patrones.server_rest.domain.adapter.AdaptadorFactura;
import com.patrones.server_rest.domain.adapter.FacturaJson;
import org.springframework.stereotype.Service;

@Service
public class ServicioFactura {

    public FacturaJson generarFactura(Factura factura) {
        FacturaJson facturaJson = new FacturaJson();
        AdaptadorFactura adaptadorFactura = new AdaptadorFactura(facturaJson);
        FacturaXml facturaJsonResponse= adaptadorFactura.convertirJson(factura);
        return adaptadorFactura.getFacturaJson();

    }
}
