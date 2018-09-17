package com.example.rocio.farmapp;

import java.io.Serializable;

public class AnalisisAgua implements Serializable {

    private int idAnalisis;
    private String composción;
    private String fecha;
    private String estadoAnalisis;
    private String horaAnalisis;

    public AnalisisAgua(int idAnalisis, String composción, String fecha, String estadoAnalisis, String horaAnalisis) {
        this.idAnalisis = idAnalisis;
        this.composción = composción;
        this.fecha = fecha;
        this.estadoAnalisis = estadoAnalisis;
        this.horaAnalisis = horaAnalisis;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public String getComposción() {
        return composción;
    }

    public void setComposción(String composción) {
        this.composción = composción;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstadoAnalisis() {
        return estadoAnalisis;
    }

    public void setEstadoAnalisis(String estadoAnalisis) {
        this.estadoAnalisis = estadoAnalisis;
    }

    public String getHoraAnalisis() {
        return horaAnalisis;
    }

    public void setHoraAnalisis(String horaAnalisis) {
        this.horaAnalisis = horaAnalisis;
    }

    @Override
    public String toString() {
        return "AnalisisAgua{" +
                "idAnalisis=" + idAnalisis +
                ", composción='" + composción + '\'' +
                ", fecha='" + fecha + '\'' +
                ", estadoAnalisis='" + estadoAnalisis + '\'' +
                ", horaAnalisis='" + horaAnalisis + '\'' +
                '}';
    }
}
