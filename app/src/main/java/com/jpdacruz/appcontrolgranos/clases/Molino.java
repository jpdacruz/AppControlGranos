package com.jpdacruz.appcontrolgranos.clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Molino extends Planta implements Serializable {

    private String categoria;
    private String proveedor;

    public Molino() {
    }

    public Molino(String numeroPlanta, String provincia, String localidad, String gpsSur, String gpsOeste, String direccion, String email, String cantInspecciones, String categoria, String proveedor) {
        super(numeroPlanta, provincia, localidad, gpsSur, gpsOeste, direccion, email, cantInspecciones);
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public Molino(String idPlanta, String numeroPlanta, String provincia, String localidad, String gpsSur, String gpsOeste, String direccion, String email, String cantInspecciones, String categoria, String proveedor) {
        super(idPlanta, numeroPlanta, provincia, localidad, gpsSur, gpsOeste, direccion, email, cantInspecciones);
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Molino{" +
                "categoria='" + categoria + '\'' +
                ", proveedor='" + proveedor + '\'' +
                '}';
    }
}