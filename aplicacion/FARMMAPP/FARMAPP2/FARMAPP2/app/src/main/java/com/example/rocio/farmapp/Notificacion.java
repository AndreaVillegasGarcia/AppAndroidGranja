package com.example.rocio.farmapp;

import java.io.Serializable;

public class Notificacion implements Serializable {
    private String fecha;
    private String hora;
    private String mensaje;
    private int tipo;

    public Notificacion(String fecha, String hora, String mensaje, int tipo) {
        this.fecha = fecha;
        this.hora = hora;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
