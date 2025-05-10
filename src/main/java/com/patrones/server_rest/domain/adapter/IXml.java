package com.patrones.server_rest.domain.adapter;

import com.patrones.server_rest.dto.Factura;

public interface IXml {
    FacturaJson convertirXml(Factura factura);

    void enviarFactura();
}
