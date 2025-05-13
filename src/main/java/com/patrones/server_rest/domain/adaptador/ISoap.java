package com.patrones.server_rest.domain.adaptador;

import com.patrones.server_rest.domain.adaptador.FacturaJson;

public interface ISoap {
    String enviarFacturaXml(String facturaXml);
}
