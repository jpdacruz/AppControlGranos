package com.jpdacruz.appcontrolgranos.clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Operador implements Serializable {

    private String numeroOperador;
    private String cuit;
    private String razonSocial;
    private String seoOperador;
    private ArrayList<Planta> plantas;

    public Operador() {
    }

    public Operador(String numeroOperador, String cuit, String razonSocial, String seoOperador, ArrayList<Planta> plantas) {
        this.numeroOperador = numeroOperador;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.seoOperador = seoOperador;
        this.plantas = plantas;
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
                "numeroOperador='" + numeroOperador + '\'' +
                ", cuit='" + cuit + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", seoOperador='" + seoOperador + '\'' +
                ", plantas=" + plantas +
                '}';
    }
}
