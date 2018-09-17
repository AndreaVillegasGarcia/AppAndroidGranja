package com.example.rocio.farmapp;

import java.io.Serializable;

public class Temperatura implements Serializable{

  private float tempMaxima;
  private float tempMinima;
  private float tempActual;
  private float humedad;

    public Temperatura(float tempMaxima, float tempMinima, float tempActual, float humedad) {
        this.tempMaxima = tempMaxima;
        this.tempMinima = tempMinima;
        this.tempActual = tempActual;
        this.humedad = humedad;
    }

    public float getTempMaxima() {
        return tempMaxima;
    }

    public void setTempMaxima(float tempMaxima) {
        this.tempMaxima = tempMaxima;
    }

    public float getTempMinima() {
        return tempMinima;
    }

    public void setTempMinima(float tempMinima) {
        this.tempMinima = tempMinima;
    }

    public float getTempActual() {
        return tempActual;
    }

    public void setTempActual(float tempActual) {
        this.tempActual = tempActual;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "tempMaxima=" + tempMaxima +
                ", tempMinima=" + tempMinima +
                ", tempActual=" + tempActual +
                ", humedad=" + humedad +
                '}';
    }
}