package com.example.rocio.farmapp;

import java.io.Serializable;

public class Nacimientos implements Serializable {

    private String raza;
    private String fecha;
    private String idMadre;
    private String corral;

    public Nacimientos(String raza, String fecha, String idMadre, String corral) {
        this.raza = raza;
        this.fecha = fecha;
        this.idMadre = idMadre;
        this.corral = corral;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdMadre() {
        return idMadre;
    }

    public void setIdMadre(String idMadre) {
        this.idMadre = idMadre;
    }

    public String getCorral() {
        return corral;
    }

    public void setCorral(String corral) {
        this.corral = corral;
    }

    @Override
    public String toString() {
        return "Nacimientos{" +
                "raza='" + raza + '\'' +
                ", fecha='" + fecha + '\'' +
                ", idMadre='" + idMadre + '\'' +
                ", corral='" + corral + '\'' +
                '}';
    }
}
