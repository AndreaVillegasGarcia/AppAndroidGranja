package com.example.rocio.farmapp;

import java.io.Serializable;

public class Plaga implements Serializable {

    private String fechaPlaga;
    private String horaPlaga;
    private int tipoPlaga;


    public Plaga (String fechaPlaga, String horaPString, int tipoPlaga){
        this.fechaPlaga = fechaPlaga;
        this.horaPlaga = horaPlaga;
        this.tipoPlaga = tipoPlaga;
    }
    public String GetFechaPlaga(){
        return this.fechaPlaga;
    }
    public String GetHoraPlaga(){
        return this.horaPlaga;
    }
    public int  GetTipoPlaga(){
        return this.tipoPlaga;
    }
    public void SetFechaPlaga(String fechaPlaga){
        this.fechaPlaga = fechaPlaga;

    }
    public void SetHoraPlaga(String horaPlaga){
        this.horaPlaga = horaPlaga;

    }
    public void SetTipoPlaga(int tipoPlaga){
        this.tipoPlaga = tipoPlaga;

    }

    @Override
    public String toString() {
        return "Plaga{" +
                "fechaPlaga='" + fechaPlaga + '\'' +
                ", horaPlaga='" + horaPlaga + '\'' +
                ", tipoPlaga=" + tipoPlaga +
                '}';
    }
}

