package com.example.rocio.farmapp;

import java.io.Serializable;

public class Animal implements Serializable {

    private String fechaNacimiento;
    private String codExplotacion;
    private String raza;
    private String sexo;
    private String observaciones;
    private int tipoAnimal;


    public Animal(String fechaNacimiento, String codExplotacion, String raza, String sexo, String observaciones, int tipoAnimal) {
        this.fechaNacimiento = fechaNacimiento;
        this.codExplotacion = codExplotacion;
        this.raza = raza;
        this.sexo = sexo;
        this.observaciones = observaciones;
        this.tipoAnimal = tipoAnimal;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCodExplotacion() {
        return codExplotacion;
    }

    public void setCodExplotacion(String codExplotacion) {
        this.codExplotacion = codExplotacion;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(int tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    @Override
    public String toString() {
        return "Animales{" +
                "fechaNacimiento='" + fechaNacimiento + '\'' +
                ", codExplotacion=" + codExplotacion +
                ", raza='" + raza + '\'' +
                ", sexo='" + sexo + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", tipoAnimal=" + tipoAnimal +
                '}';
    }
}

