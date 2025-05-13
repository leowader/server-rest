package com.patrones.server_rest.domain.adaptador;

import com.patrones.server_rest.domain.adaptador.FacturaJson;
import com.patrones.server_rest.domain.Factura;

public interface IFacturaAdaptador {
    FacturaJson procesarFactura(Factura factura);
}
