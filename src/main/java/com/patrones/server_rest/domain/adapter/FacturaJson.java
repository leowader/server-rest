package com.patrones.server_rest.domain.adapter;

public class FacturaJson implements  IJson{
    @Override
    public void convertirJson() {
        System.out.println("CONVIRTIENDO FACTURA JSON");
    }
}
