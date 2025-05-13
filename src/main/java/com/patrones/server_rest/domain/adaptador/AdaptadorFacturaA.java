package com.patrones.server_rest.domain.adaptador;

import com.patrones.server_rest.domain.adaptador.FacturaJson;
import com.patrones.server_rest.domain.Factura;

public class AdaptadorFacturaA implements IFacturaAdaptador {
    FacturaJson facturaJson;

    public AdaptadorFacturaA(com.patrones.server_rest.domain.adaptador.FacturaJson facturaJson) {
        this.facturaJson = facturaJson;
    }

    @Override
    public FacturaJson procesarFactura(Factura factura) {
        FacturaSoap facturaXml = facturaJson.convertirFacturaJsonToXml(factura);
        return this.facturaJson.convertirFacturaXmlToJson(facturaXml.enviarFacturaXml(facturaXml.getFacturaXml()));
    }
}
