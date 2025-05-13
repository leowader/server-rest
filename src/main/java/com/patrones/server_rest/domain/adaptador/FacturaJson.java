package com.patrones.server_rest.domain.adaptador;

import com.patrones.server_rest.domain.Emisor;
import com.patrones.server_rest.domain.Producto;
import com.patrones.server_rest.domain.Receptor;
import com.patrones.server_rest.domain.adaptador.FacturaJson;
import com.patrones.server_rest.domain.Factura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.json.XML;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaJson extends Factura implements IFactura {
    private String firmaDigital;

    @Override
    public FacturaSoap convertirFacturaJsonToXml(Factura facturaJson) {
        StringBuilder xml = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fac=\"http://example.com/factura\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <fac:facturaRequest>\n" +
                "         <fac:emisor>\n" +
                "            <fac:nombre>" + facturaJson.getEmisor().getNombre() + "</fac:nombre>\n" +
                "            <fac:nit>" + facturaJson.getEmisor().getNit() + "</fac:nit>\n" +
                "            <fac:direccion>" + facturaJson.getEmisor().getDireccion() + "</fac:direccion>\n" +
                "            <fac:telefono>" + facturaJson.getEmisor().getTelefono() + "</fac:telefono>\n" +
                "         </fac:emisor>\n" +
                "         <fac:receptor>\n" +
                "            <fac:nombre>" + facturaJson.getReceptor().getNombre() + "</fac:nombre>\n" +
                "            <fac:nit>" + facturaJson.getReceptor().getNit() + "</fac:nit>\n" +
                "         </fac:receptor>\n" +
                "         <fac:fechaEmision>" + facturaJson.getFechaEmision() + "</fac:fechaEmision>\n" +
                "         <fac:numeroFactura>" + facturaJson.getNumeroFactura() + "</fac:numeroFactura>\n" +
                "         <fac:productos>\n");

        for (var producto : facturaJson.getProductos()) {
            xml.append("            <fac:producto>\n" + "               <fac:descripcion>").append(producto.getDescripcion()).append("</fac:descripcion>\n").append("               <fac:cantidad>").append(producto.getCantidad()).append("</fac:cantidad>\n").append("               <fac:precioUnitario>").append(producto.getPrecioUnitario()).append("</fac:precioUnitario>\n").append("               <fac:totalProducto>").append(producto.getTotalProducto()).append("</fac:totalProducto>\n").append("            </fac:producto>\n");
        }

        xml.append("         </fac:productos>\n" + "         <fac:total>").append(facturaJson.getTotal()).append("</fac:total>\n").append("      </fac:facturaRequest>\n").append("   </soapenv:Body>\n").append("</soapenv:Envelope>");
        return new FacturaSoap(xml.toString());
    }

    @Override
    public FacturaJson convertirFacturaXmlToJson(String facturaXml) {
        JSONObject jsonObject = XML.toJSONObject(facturaXml);
        JSONObject facturaResp = jsonObject
                .getJSONObject("SOAP-ENV:Envelope")
                .getJSONObject("SOAP-ENV:Body")
                .getJSONObject("ns2:facturaResponse");

        FacturaJson facturaJson = new FacturaJson();
        facturaJson.setNumeroFactura(facturaResp.getString("ns2:numeroFactura"));
        facturaJson.setFechaEmision(LocalDate.parse(facturaResp.optString("ns2:fechaEmision")));
        facturaJson.setTotal(facturaResp.getDouble("ns2:total"));
        facturaJson.setFirmaDigital(facturaResp.optString("ns2:firmaDigital"));

        // Receptor
        JSONObject receptorObj = facturaResp.getJSONObject("ns2:receptor");
        Receptor receptor = new Receptor();
        receptor.setNombre(receptorObj.getString("ns2:nombre"));
        receptor.setNit(receptorObj.getString("ns2:nit"));
        facturaJson.setReceptor(receptor);

        // Emisor
        JSONObject emisorObj = facturaResp.getJSONObject("ns2:emisor");
        Emisor emisor = new Emisor();
        emisor.setNombre(emisorObj.getString("ns2:nombre"));
        emisor.setTelefono(String.valueOf(emisorObj.getInt("ns2:telefono")));
        emisor.setDireccion(emisorObj.getString("ns2:direccion"));
        emisor.setNit(emisorObj.getString("ns2:nit"));
        facturaJson.setEmisor(emisor);

        // Productos
        List<Producto> productos = new java.util.ArrayList<>();
        Object productoObj = facturaResp
                .getJSONObject("ns2:productos")
                .get("ns2:producto");

        if (productoObj instanceof JSONObject) {
            // Solo hay un producto
            productos.add(parseProducto((JSONObject) productoObj));
        } else if (productoObj instanceof org.json.JSONArray productosArray) {
            // Hay múltiples productos
            for (int i = 0; i < productosArray.length(); i++) {
                productos.add(parseProducto(productosArray.getJSONObject(i)));
            }
        }

        facturaJson.setProductos(productos);
        return facturaJson;
    }

    private Producto parseProducto(JSONObject json) {
        Producto producto = new Producto();
        producto.setCantidad(json.getInt("ns2:cantidad"));
        producto.setDescripcion(json.getString("ns2:descripcion"));
        producto.setPrecioUnitario(json.getDouble("ns2:precioUnitario"));
        producto.setTotalProducto(json.getDouble("ns2:totalProducto"));
        return producto;
    }
}