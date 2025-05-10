package com.patrones.server_rest.domain.adapter;

public class AdaptadorFactura implements IXml {
    FacturaJson facturaJson;

    public AdaptadorFactura(FacturaJson facturaJson) {
        this.facturaJson = facturaJson;
    }


    @Override
    public void convertirAXml() {
        this.facturaJson.convertirJson();
    }
}
