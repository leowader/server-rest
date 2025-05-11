package com.patrones.server_rest.domain.adapter;

import com.patrones.server_rest.domain.Emisor;
import com.patrones.server_rest.domain.Producto;
import com.patrones.server_rest.domain.Receptor;
import lombok.Getter;
import lombok.Setter;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.json.XML;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter

public class FacturaXml implements IXml {

    private String factura;

    public FacturaXml(String factura) {
        this.factura = factura;

    }


    @SneakyThrows
    @Override
    public FacturaJson convertirXml(String factura) {
        JSONObject jsonObject = XML.toJSONObject(factura);
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