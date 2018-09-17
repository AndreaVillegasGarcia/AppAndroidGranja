package com.example.rocio.farmapp;

import java.io.Serializable;

public class Dispensacion implements Serializable {

    private String composicion;
    private String hora;
    private String fecha;
    private float cantidad;

    public Dispensacion(String composicion, String hora, String fecha, float cantidad) {
        this.composicion = composicion;
        this.hora = hora;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Dispensacion{" +
                "composicion='" + composicion + '\'' +
                ", hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
