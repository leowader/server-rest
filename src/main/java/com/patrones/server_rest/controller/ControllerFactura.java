package com.patrones.server_rest.controller;

import com.patrones.server_rest.domain.Factura;
import com.patrones.server_rest.domain.adapter.AdaptadorFactura;
import com.patrones.server_rest.domain.adapter.FacturaJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerFactura {


    @PostMapping("/factura")
    public Factura getFactura(@RequestBody Factura factura) {
        FacturaJson facturaJson = new FacturaJson();
        AdaptadorFactura adaptadorFactura =new AdaptadorFactura(facturaJson);
        adaptadorFactura.convertirAXml();
        return factura;
    }
}
