package com.example.rocio.farmapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Corral implements Serializable {

    private int numCorral;
    private String localizacion;

    private int maxAnimales=10;
    private ArrayList<Animal> animal;
    private ArrayList<Limpiezas> limpieza = new ArrayList<Limpiezas>();
    private ArrayList<Plaga> plaga = new ArrayList<Plaga>();
    private ArrayList<AnalisisAgua> analisisAguas = new ArrayList<AnalisisAgua>();
    private ArrayList<Dispensacion> dispensaciones = new ArrayList<Dispensacion>();

    private Temperatura temperatura;

    public Corral(int numCorral, String localizacion, ArrayList<Animal> animal,
                  ArrayList<Limpiezas> limpieza, ArrayList<Plaga> plaga, ArrayList<AnalisisAgua> analisisAguas,
                  ArrayList<Dispensacion> dispensaciones, Temperatura temperatura) {
        this.numCorral = numCorral;
        this.localizacion = localizacion;
        this.animal = animal;
        this.limpieza = limpieza;
        this.plaga = plaga;
        this.analisisAguas = analisisAguas;
        this.dispensaciones = dispensaciones;
        this.temperatura = temperatura;
    }
    public Corral(ArrayList<Animal> animal, int id, String localizacion){
        this.animal = animal;
        this.numCorral = id;
        this.localizacion = localizacion;
        limpieza.add(new Limpiezas("01/01/2000", "23:45", 0));
        limpieza.add(new Limpiezas("01/01/2004", "23:00", 1));
        plaga.add(new Plaga("01/01/200"+numCorral, "23:45", 1));
        plaga.add(new Plaga("01/01/201"+numCorral, "23:45", 0));
        analisisAguas.add(new AnalisisAgua(numCorral, "Sodio = 25%, Fosforo = 25%, Calcio = 25%, Otros = 25%",
                "01/01/200"+numCorral,"Óptimo",  "23:45"));
        dispensaciones.add(new Dispensacion("Pienso natural = 100%", "23:45","01/01/200"+numCorral, 12));
        temperatura = new Temperatura(32.0f,10.0f,20.0f,36.0f);

    }

    public int getNumCorral() {
        return numCorral;
    }

    public void setNumCorral(int numCorral) {
        this.numCorral = numCorral;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public int getMaxAnimales() {
        return maxAnimales;
    }

    public void setMaxAnimales(int maxAnimales) {
        this.maxAnimales = maxAnimales;
    }

    public ArrayList<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(ArrayList<Animal> animal) {
        this.animal = animal;
    }

    public ArrayList<Limpiezas> getLimpieza() {
        return limpieza;
    }

    public void setLimpieza(ArrayList<Limpiezas> limpieza) {
        this.limpieza = limpieza;
    }

    public ArrayList<Plaga> getPlaga() {
        return plaga;
    }

    public void setPlaga(ArrayList<Plaga> plaga) {
        this.plaga = plaga;
    }

    public ArrayList<AnalisisAgua> getAnalisisAguas() {
        return analisisAguas;
    }

    public void setAnalisisAguas(ArrayList<AnalisisAgua> analisisAguas) {
        this.analisisAguas = analisisAguas;
    }

    public ArrayList<Dispensacion> getDispensaciones() {
        return dispensaciones;
    }

    public void setDispensaciones(ArrayList<Dispensacion> dispensaciones) {
        this.dispensaciones = dispensaciones;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public void addAnimal(Animal animal){

        try {
            if (this.animal.size() < this.maxAnimales)
                this.animal.add(animal);
            else throw new IndexOutOfBoundsException();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarAnimal(int i){
        try {
            if (this.animal.size() == 0)
                this.animal.remove(i);
            else throw new IndexOutOfBoundsException();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addLimpieza(Limpiezas limpieza){
        this.limpieza.add(limpieza);

    }
    public void addPlaga(Plaga plaga){
        this.plaga.add(plaga);
    }
    public void addAnalisis(AnalisisAgua analisisAgua){
        this.analisisAguas.add(analisisAgua);
    }
    public void addDispensacion(Dispensacion disp){
        this.dispensaciones.add(disp);

    }

    @Override
    public String toString() {
        return "Corral{" +
                "\n numCorral=" + numCorral +
                ", localizacion='" + localizacion + '\'' +
                ", maxAnimales=" + maxAnimales +
                ", animal=" + animal +
                ", limpieza=" + limpieza +
                ", plaga=" + plaga +
                ", analisisAguas=" + analisisAguas +
                ", dispensaciones=" + dispensaciones +
                ", temperatura=" + temperatura +
                '}';
    }

    public ArrayList<Limpiezas> getLimpiezas(){
        ArrayList<Limpiezas> aux = new ArrayList<Limpiezas>();
        for (int i = 0; i< limpieza.size(); i++){
            if(limpieza.get(i).getTipo() == 0){
                aux.add(limpieza.get(i));
            }
        }
        return aux;
    }
    public ArrayList<Limpiezas> getDesinfecciones(){
        ArrayList<Limpiezas> aux = new ArrayList<Limpiezas>();
        for (int i = 0; i< limpieza.size(); i++){
            if(limpieza.get(i).getTipo() == 1){
                aux.add(limpieza.get(i));
            }
        }
        return aux;
    }

    public ArrayList<Plaga> getPlagas(){
        ArrayList<Plaga> aux = new ArrayList<>();
        for (int i = 0; i< plaga.size(); i++){
            if(plaga.get(i).GetTipoPlaga() == 0){
                aux.add(plaga.get(i));
            }
        }
        return aux;
    }
    public ArrayList<Plaga> getDesratizacion(){
        ArrayList<Plaga> aux = new ArrayList<>();
        for (int i = 0; i< plaga.size(); i++){
            if(plaga.get(i).GetTipoPlaga() == 1){
                aux.add(plaga.get(i));
            }
        }
        return aux;
    }
}

