package com.patrones.server_rest.domain.adapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patrones.server_rest.dto.Factura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@AllArgsConstructor
public class FacturaJson extends Factura implements IJson {
    private String firmaDigital;
    @JsonIgnore
    private String factura;
    @JsonIgnore
    private String facturaXmlResponse;
    @JsonIgnore
    private RestTemplate restTemplate;

    public FacturaJson() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public FacturaXml convertirJson(Factura factura) {
        StringBuilder xml = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fac=\"http://example.com/factura\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <fac:facturaRequest>\n" +
                "         <fac:emisor>\n" +
                "            <fac:nombre>" + factura.getEmisor().getNombre() + "</fac:nombre>\n" +
                "            <fac:nit>" + factura.getEmisor().getNit() + "</fac:nit>\n" +
                "            <fac:direccion>" + factura.getEmisor().getDireccion() + "</fac:direccion>\n" +
                "            <fac:telefono>" + factura.getEmisor().getTelefono() + "</fac:telefono>\n" +
                "         </fac:emisor>\n" +
                "         <fac:receptor>\n" +
                "            <fac:nombre>" + factura.getReceptor().getNombre() + "</fac:nombre>\n" +
                "            <fac:nit>" + factura.getReceptor().getNit() + "</fac:nit>\n" +
                "         </fac:receptor>\n" +
                "         <fac:fechaEmision>" + factura.getFechaEmision() + "</fac:fechaEmision>\n" +
                "         <fac:numeroFactura>" + factura.getNumeroFactura() + "</fac:numeroFactura>\n" +
                "         <fac:productos>\n");

        for (var producto : factura.getProductos()) {
            xml.append("            <fac:producto>\n" + "               <fac:descripcion>").append(producto.getDescripcion()).append("</fac:descripcion>\n").append("               <fac:cantidad>").append(producto.getCantidad()).append("</fac:cantidad>\n").append("               <fac:precioUnitario>").append(producto.getPrecioUnitario()).append("</fac:precioUnitario>\n").append("               <fac:totalProducto>").append(producto.getTotalProducto()).append("</fac:totalProducto>\n").append("            </fac:producto>\n");
        }

        xml.append("         </fac:productos>\n" + "         <fac:total>").append(factura.getTotal()).append("</fac:total>\n").append("      </fac:facturaRequest>\n").append("   </soapenv:Body>\n").append("</soapenv:Envelope>");
        this.factura = xml.toString();
        return new FacturaXml(xml.toString());
    }

    @Override
    public void enviarFactura() {
        //ENVIA FACTURA AL SERVICIO SOAP EN FORMATO XML Y DEVUELVE UN XML "FIRMADO DIGITALMENTE"
        String soapEndpointUrl = "http://localhost:8080/ws";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/xml;charset=UTF-8");
        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(this.factura, headers);
        this.facturaXmlResponse = restTemplate.exchange(soapEndpointUrl, HttpMethod.POST, entity, String.class).getBody();
        System.out.println("FIRMANDO DIGITALMENTE");
    }
}