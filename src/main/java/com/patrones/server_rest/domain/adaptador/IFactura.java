package com.patrones.server_rest.domain.adaptador;

import com.patrones.server_rest.domain.adaptador.FacturaJson;
import com.patrones.server_rest.domain.Factura;

public interface IFactura {

    FacturaSoap convertirFacturaJsonToXml (Factura facturaJson );
    com.patrones.server_rest.domain.adaptador.FacturaJson convertirFacturaXmlToJson(String facturaXml);

}
