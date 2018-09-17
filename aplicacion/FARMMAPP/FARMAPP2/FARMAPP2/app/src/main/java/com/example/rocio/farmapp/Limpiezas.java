package com.example.rocio.farmapp;

import java.io.Serializable;

public class Limpiezas implements Serializable{

   private String fecha;
   private String hora;
   private int tipo;

    public Limpiezas(String fecha, String hora, int tipo) {
        this.fecha = fecha;
        this.hora = hora;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Limpieza{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}