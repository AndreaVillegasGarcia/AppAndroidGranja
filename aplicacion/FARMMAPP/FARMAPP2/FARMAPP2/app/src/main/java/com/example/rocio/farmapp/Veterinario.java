package com.example.rocio.farmapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Veterinario implements Serializable {

    private ArrayList<Vacunas> vacunas = new ArrayList<Vacunas>();
    private ArrayList<Animal> enGestacion = new ArrayList<Animal>();
    private ArrayList<Nacimientos>nacimientos = new ArrayList<Nacimientos>();

    public Veterinario(ArrayList<Vacunas> vacunas, ArrayList<Animal> enGestacion, ArrayList<Nacimientos> nacimientos) {
        this.vacunas = vacunas;
        this.enGestacion = enGestacion;
        this.nacimientos = nacimientos;
    }

    public Veterinario(Animal animal) {

        for(int i = 0; i < 3; i++){
            vacunas.add(new Vacunas(i, animal,"2006/12/24" ));
            nacimientos.add(new Nacimientos("Vaca","20/08/2006", ""+i,""+i  ));

        }
    }

    public Veterinario(Animal animal1, Animal animal2,Animal animal3,Animal animal4,Animal animal5,Animal animal6) {

        for(int i = 0; i < 3; i++){
            vacunas.add(new Vacunas(i, animal1,"2019-08-19" ));
            nacimientos.add(new Nacimientos("Vaca","20/08/2006", ""+i,""+i  ));
        }

        enGestacion.add(animal1);
        enGestacion.add(animal2);
        enGestacion.add(animal3);
        enGestacion.add(animal4);
        enGestacion.add(animal5);
        enGestacion.add(animal6);
    }

    public ArrayList<Vacunas> getVacunas() {
        return vacunas;
    }

    public void setVacunas(ArrayList<Vacunas> vacunas) {
        this.vacunas = vacunas;
    }

    public ArrayList<Animal> getEnGestacion() {
        return enGestacion;
    }

    public void setEnGestacion(ArrayList<Animal> enGestacion) {
        this.enGestacion = enGestacion;
    }

    public ArrayList<Nacimientos> getNacimientos() {
        return nacimientos;
    }

    public void setNacimientos(ArrayList<Nacimientos> nacimientos) {
        this.nacimientos = nacimientos;
    }

    public void addAnimal(Animal animal){
        this.enGestacion.add(animal);
    }
    public void darBaja(int i){
        try {
            if (this.enGestacion.size() == 0)
                this.enGestacion.remove(i);
            else throw new IndexOutOfBoundsException();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addVacuna(Vacunas vacuna){
        this.vacunas.add(vacuna);
    }

    public int eliminargestacion(Animal animal){
        int indice = -1;
        for(int i = 0; i< enGestacion.size() ; i++){
            if(enGestacion.get(i).equals(animal))
                indice = i;
        }
        return indice;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "vacunas=" + vacunas +
                ", enGestacion=" + enGestacion +
                ", nacimientos=" + nacimientos +
                '}';
    }
}
