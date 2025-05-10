package com.patrones.server_rest.controller;

import com.patrones.server_rest.dto.Factura;
import com.patrones.server_rest.services.ServicioFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerFactura {

    @Autowired
    ServicioFactura servicioFactura;

    @PostMapping("/factura")
    public Factura generarFactura(@RequestBody Factura factura) {
        servicioFactura.generarFactura(factura);
        return factura;
    }
}
