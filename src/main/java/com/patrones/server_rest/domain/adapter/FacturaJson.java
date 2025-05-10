package com.patrones.server_rest.domain.adapter;

import com.patrones.server_rest.dto.Factura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaJson extends Factura implements IJson {
    private String firmaDigital;
    @Override
    public FacturaXml convertirJson(Factura factura) {
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fac=\"http://example.com/factura\">\n" +
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
                "         <fac:productos>\n";

        for (var producto : factura.getProductos()) {
            xml += "            <fac:producto>\n" +
                    "               <fac:descripcion>" + producto.getDescripcion() + "</fac:descripcion>\n" +
                    "               <fac:cantidad>" + producto.getCantidad() + "</fac:cantidad>\n" +
                    "               <fac:precioUnitario>" + producto.getPrecioUnitario() + "</fac:precioUnitario>\n" +
                    "               <fac:totalProducto>" + producto.getTotalProducto() + "</fac:totalProducto>\n" +
                    "            </fac:producto>\n";
        }

        xml += "         </fac:productos>\n" +
                "         <fac:total>" + factura.getTotal() + "</fac:total>\n" +
                "      </fac:facturaRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        System.out.println("CONVIRTIENDO FACTURA JSON A XML...");
        return new FacturaXml(xml);
    }
}