package com.patrones.server_rest.domain.adaptador;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
public class FacturaSoap implements ISoap {
    @JsonIgnore
    private RestTemplate restTemplate;
    private String facturaXml;

    public FacturaSoap() {
    }

    public FacturaSoap(String facturaXml) {
        this.facturaXml = facturaXml;
        this.restTemplate = new RestTemplate();

    }


    @Override
    public String enviarFacturaXml(String facturaXml) {
        String soapEndpointUrl = "http://localhost:8080/ws";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/xml;charset=UTF-8");
        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(facturaXml, headers);
        String responseSoap = this.restTemplate.exchange(soapEndpointUrl, HttpMethod.POST, entity, String.class).getBody();
        this.setFacturaXml(responseSoap);
        System.out.println("FIRMANDO DIGITALMENTE");
        return responseSoap;
    }
}
