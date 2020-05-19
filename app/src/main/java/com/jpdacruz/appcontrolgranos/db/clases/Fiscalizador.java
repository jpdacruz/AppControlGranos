package com.jpdacruz.appcontrolgranos.db.clases;

public class Fiscalizador {

    private String id;
    private String agencia;
    private String nombre;

    public Fiscalizador() {
    }

    public Fiscalizador(String agencia,String nombre) {
        this.agencia = agencia;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return agencia + " " + nombre;
    }
}

