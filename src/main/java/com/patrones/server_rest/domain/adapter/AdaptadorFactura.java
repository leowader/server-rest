package com.patrones.server_rest.domain.adapter;

import com.patrones.server_rest.dto.Factura;
import lombok.Getter;

public class AdaptadorFactura implements IJson {
    @Getter
    FacturaJson facturaJson;
    FacturaXml facturaXml;

    public AdaptadorFactura(FacturaJson facturaJson) {
        this.facturaJson = facturaJson;
    }

    @Override
    public FacturaXml convertirJson(Factura factura) {
        //CONVIERTE JSON A XML
        this.facturaXml = this.facturaJson.convertirJson(factura);
        this.enviarFactura();
        //CONVIERTE XML A JSON
        this.facturaJson = this.facturaXml.convertirXml(facturaJson.getFacturaXmlResponse());
        return this.facturaXml;
    }

    @Override
    public void enviarFactura() {
        this.facturaJson.enviarFactura();
    }
}
