package com.example.rocio.farmapp;

import java.io.Serializable;

public class Gastos implements Serializable {

    private float gPienso;
    private float gAgua;
    private float gUtiles;
    private float gVeterinario;


    public Gastos(int gPienso, int gAgua, int gUtiles, int gVeterinario) {
        this.gPienso = gPienso;
        this.gAgua = gAgua;
        this.gUtiles = gUtiles;
        this.gVeterinario = gVeterinario;
    }

    public float getgPienso() {
        return gPienso;
    }

    public void setgPienso(int gPienso) {
        this.gPienso = gPienso;
    }

    public float getgAgua() {
        return gAgua;
    }

    public void setgAgua(int gAgua) {
        this.gAgua = gAgua;
    }

    public float getgUtiles() {
        return gUtiles;
    }

    public void setgUtiles(int gUtiles) {
        this.gUtiles = gUtiles;
    }

    public float getgVeterinario() {
        return gVeterinario;
    }

    public void setgVeterinario(int gVeterinario) {
        this.gVeterinario = gVeterinario;
    }

    public void addGasto(int tipo, float cantidad){

        switch(tipo){
            case 0:this.gPienso = gPienso + cantidad;break;
            case 1:this.gAgua = gAgua + cantidad;break;
            case 2:this.gUtiles = gUtiles + cantidad;break;
            case 3:this.gVeterinario = gVeterinario + cantidad;break;

            // 1- gatos de pienso, 2-gastos de agua, 3-gatos varios, 4- gastos veterinarios

        }
    }
    public float getTotal(){
        return gAgua+gPienso+gUtiles+gVeterinario;
    }
    public void reset(){
        this.gPienso = 0;
        this.gAgua = 0;
        this.gUtiles = 0;
        this.gVeterinario = 0;
    }

    @Override
    public String toString() {
        return "Gastos{" +
                "gPienso=" + gPienso +
                ", gAgua=" + gAgua +
                ", gUtiles=" + gUtiles +
                ", gVeterinario=" + gVeterinario +
                '}';
    }
}
