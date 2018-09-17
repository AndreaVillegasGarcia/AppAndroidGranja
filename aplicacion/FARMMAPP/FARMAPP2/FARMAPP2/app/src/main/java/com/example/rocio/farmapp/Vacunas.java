package com.example.rocio.farmapp;

import java.io.Serializable;

public class Vacunas implements Serializable {

    private int idVacuna;
    private Animal animal;
    private String fecha;

    public Vacunas(int idVacuna, Animal animal, String fecha) {
        this.idVacuna = idVacuna;
        this.animal = animal;
        this.fecha = fecha;
    }


    public int getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(int idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Vacunas{" +
                "idVacuna=" + idVacuna +
                ", animal=" + animal +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
