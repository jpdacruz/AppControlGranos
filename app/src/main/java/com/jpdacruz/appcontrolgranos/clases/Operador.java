package com.jpdacruz.appcontrolgranos.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Operador implements Serializable {

    private String id;
    private String numeroOperador;
    private String cuit;
    private String razonSocial;
    private String seoOperador;
    private ArrayList<Planta> plantas;

    public Operador() {
    }

    public Operador(String id, String numeroOperador, String cuit, String razonSocial, String seoOperador, ArrayList<Planta> plantas) {
        this.id = id;
        this.numeroOperador = numeroOperador;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.seoOperador = seoOperador;
        this.plantas = plantas;
    }

    public Operador(String numeroOperador, String cuit, String razonSocial, String seoOperador, ArrayList<Planta> plantas) {
        this.numeroOperador = numeroOperador;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.seoOperador = seoOperador;
        this.plantas = plantas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroOperador() {
        return numeroOperador;
    }

    public void setNumeroOperador(String numeroOperador) {
        this.numeroOperador = numeroOperador;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSeoOperador() {
        return seoOperador;
    }

    public void setSeoOperador(String seoOperador) {
        this.seoOperador = seoOperador;
    }

    public ArrayList<Planta> getPlantas() {
        return plantas;
    }

    public void setPlantas(ArrayList<Planta> plantas) {
        this.plantas = plantas;
    }

    @Override
    public String toString() {
        return "Operador{" +
                "id='" + id + '\'' +
                ", numeroOperador='" + numeroOperador + '\'' +
                ", cuit='" + cuit + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", seoOperador='" + seoOperador + '\'' +
                ", plantas=" + plantas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operador operador = (Operador) o;
        return id.equals(operador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
