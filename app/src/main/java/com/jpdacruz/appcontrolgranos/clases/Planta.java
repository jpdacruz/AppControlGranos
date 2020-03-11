package com.jpdacruz.appcontrolgranos.clases;

import java.util.ArrayList;
import java.util.Objects;

public class Planta extends Operador{

    private String idPlanta;
    private String numeroPlanta;
    private String provincia;
    private String localidad;
    private String gpsSur;
    private String gpsOeste;
    private String direccion;
    private String email;
    private String cantInspecciones;

    public Planta() {
    }

    public Planta(String idPlanta, String numeroPlanta, String provincia, String localidad, String gpsSur, String gpsOeste, String direccion, String email, String cantInspecciones) {
        this.idPlanta = idPlanta;
        this.numeroPlanta = numeroPlanta;
        this.provincia = provincia;
        this.localidad = localidad;
        this.gpsSur = gpsSur;
        this.gpsOeste = gpsOeste;
        this.direccion = direccion;
        this.email = email;
        this.cantInspecciones = cantInspecciones;
    }

    public Planta(String numeroPlanta, String provincia, String localidad, String gpsSur, String gpsOeste, String direccion, String email, String cantInspecciones) {
        this.numeroPlanta = numeroPlanta;
        this.provincia = provincia;
        this.localidad = localidad;
        this.gpsSur = gpsSur;
        this.gpsOeste = gpsOeste;
        this.direccion = direccion;
        this.email = email;
        this.cantInspecciones = cantInspecciones;
    }

    public String getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(String idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getNumeroPlanta() {
        return numeroPlanta;
    }

    public void setNumeroPlanta(String numeroPlanta) {
        this.numeroPlanta = numeroPlanta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getGpsSur() {
        return gpsSur;
    }

    public void setGpsSur(String gpsSur) {
        this.gpsSur = gpsSur;
    }

    public String getGpsOeste() {
        return gpsOeste;
    }

    public void setGpsOeste(String gpsOeste) {
        this.gpsOeste = gpsOeste;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCantInspecciones() {
        return cantInspecciones;
    }

    public void setCantInspecciones(String cantInspecciones) {
        this.cantInspecciones = cantInspecciones;
    }

    @Override
    public String toString() {
        return "Planta{" +
                "idPlanta='" + idPlanta + '\'' +
                ", numeroPlanta='" + numeroPlanta + '\'' +
                ", provincia='" + provincia + '\'' +
                ", localidad='" + localidad + '\'' +
                ", gpsSur='" + gpsSur + '\'' +
                ", gpsOeste='" + gpsOeste + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", cantInspecciones='" + cantInspecciones + '\'' +
                '}';
    }
}
