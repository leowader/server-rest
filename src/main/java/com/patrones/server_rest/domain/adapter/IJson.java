package com.patrones.server_rest.domain.adapter;

import com.patrones.server_rest.dto.Factura;

public interface IJson {
    FacturaXml convertirJson(Factura factura);
    void enviarFactura();
}
