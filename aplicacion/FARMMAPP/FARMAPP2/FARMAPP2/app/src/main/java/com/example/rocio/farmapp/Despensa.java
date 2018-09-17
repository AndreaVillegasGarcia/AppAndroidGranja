package com.example.rocio.farmapp;

import java.io.Serializable;

public class Despensa implements Serializable {

    private int cantidadComida;
    private int cantidadAgua;

    public Despensa(int cantidadComida, int cantidadAgua) {
        this.cantidadComida = cantidadComida;
        this.cantidadAgua = cantidadAgua;
    }

    public String getCantidadComida() {
        return ""+cantidadComida;
    }

    public void setCantidadComida(int cantidadComida) {
        this.cantidadComida = cantidadComida;
    }

    public String getCantidadAgua() {
        return "" + cantidadAgua;
    }

    public void setCantidadAgua(int cantidadAgua) {
        this.cantidadAgua = cantidadAgua;
    }

    public void addComida(int cantidad){
         this.cantidadComida = cantidadComida + cantidad;
    }

    public void quitarComida(int cantidad){
        this.cantidadComida = cantidadComida - cantidad;
    }

    public void addAgua(int cantidad){
        this.cantidadAgua = cantidadAgua + cantidad;
    }
    public void quitarAgua(int cantidad){
        this.cantidadAgua = cantidadAgua - cantidad;
    }

    @Override
    public String toString() {
        return "Despensa{" +
                "cantidadComida=" + cantidadComida +
                ", cantidadAgua=" + cantidadAgua +
                '}';
    }
}
