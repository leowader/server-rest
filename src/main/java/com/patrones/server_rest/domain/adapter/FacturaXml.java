package com.patrones.server_rest.domain.adapter;

import com.patrones.server_rest.dto.Factura;
import com.patrones.server_rest.dto.FacturaResponse;
import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


@Getter
@Setter

public class FacturaXml implements IXml {

    private String factura;
    private RestTemplate restTemplate;
    FacturaJson facturaJson;

    public FacturaXml(String factura) {
        this.factura = factura;
        this.restTemplate = new RestTemplate();
        this.facturaJson= new FacturaJson();

    }


    @Override
    public FacturaJson convertirXml(Factura factura) {
        return this.facturaJson;
    }

    @Override
    public void enviarFactura() {
        String soapEndpointUrl = "http://localhost:8080/ws";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/xml;charset=UTF-8");
        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(this.factura, headers);
        String response = restTemplate.exchange(soapEndpointUrl, HttpMethod.POST, entity, String.class).getBody();
        this.facturaJson.setFirmaDigital(response);
        System.out.println("FIRMANDO DIGITALMENTE");
    }

}