package com.patrones.server_rest.domain.adapter;

import com.patrones.server_rest.dto.Factura;

public class AdaptadorFactura implements IXml {
    FacturaJson facturaJson;
    FacturaXml facturaXml;

    public AdaptadorFactura(FacturaJson facturaJson) {
        this.facturaJson = facturaJson;
    }

    @Override
    public FacturaJson convertirXml(Factura factura) {
        //CONVIERTE EL JSON A XML Y DEVUELVE UN JSON
        this.facturaXml = this.facturaJson.convertirJson(factura);
        this.enviarFactura();
        return this.facturaXml.getFacturaJson();
    }

    @Override
    public void enviarFactura() {
        this.facturaXml.enviarFactura();
    }
}
