package com.example.rocio.farmapp;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("MiGranja")

public class MiGranja implements Serializable  {

    private ArrayList<Corral> corrales = new ArrayList<Corral>();
    private Despensa despensa;
    private Veterinario veterinario;
    private Gastos gastos;
    private ArrayList<Animal> vacas = new ArrayList<Animal>();
    private ArrayList<Animal> cerdos = new ArrayList<Animal>();
    private ArrayList<Animal> gallinas = new ArrayList<Animal>();
    private ArrayList<Animal> ovejas = new ArrayList<Animal>();
    private ArrayList<Animal> cabras = new ArrayList<Animal>();
    private ArrayList<Animal> otros = new ArrayList<Animal>();
    private ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();

    public MiGranja(ArrayList<Corral> corrales, Despensa despensa, Veterinario veterinario, Gastos gastos, ArrayList<Animal> vacas,
                    ArrayList<Animal> cerdos, ArrayList<Animal> gallinas, ArrayList<Animal> ovejas, ArrayList<Animal> cabras, ArrayList<Animal> otros) {
        this.corrales = corrales;
        this.despensa = despensa;
        this.veterinario = veterinario;
        this.gastos = gastos;
        this.vacas = vacas;
        this.cerdos = cerdos;
        this.gallinas = gallinas;
        this.ovejas = ovejas;
        this.cabras = cabras;
        this.otros = otros;
    }

    public MiGranja() {
        int id = 0;
            for(int j = 0; j<6;j++) {
                vacas.add(new Animal("01/01/2000", ""+ id++, "Vaca", "Hembra", "No hay", 0));
                cerdos.add(new Animal("01/01/2000", ""+ id++, "Cerdo", "macho", "No hay", 1));
                gallinas.add(new Animal("01/01/2000",""+ id++, "Gallina", "Hembra", "No hay", 2));
                ovejas.add(new Animal("01/01/2000", ""+ id++, "Ovejas", "Hembra", "No hay", 3));
                cabras.add(new Animal("01/01/2000", ""+ id++, "Cabras", "Hembra", "No hay", 4));
                otros.add(new Animal("01/01/2000", ""+ id++, "Mulas", "Hembra", "No hay", 5));
            }
            for(int j = 0; j<6;j++){
                switch(j){
                    case 0: corrales.add(new Corral(vacas,j,"Ala norte"));break;
                    case 1: corrales.add(new Corral(cerdos,j,"Ala sur"));break;
                    case 2: corrales.add(new Corral(gallinas,j,"Ala este")) ;break;
                    case 3: corrales.add(new Corral(ovejas,j,"Ala oeste"));break;
                    case 4: corrales.add(new Corral(cabras,j,"Ala noreste")) ;break;
                    case 5: corrales.add(new Corral(otros,j,"Ala suroeste"));break;
                }
            }
            despensa = new Despensa(100, 50);
        veterinario = new Veterinario(vacas.get(0), cerdos.get(0), gallinas.get(0), ovejas.get(0), cabras.get(0), otros.get(0));
            gastos = new Gastos(10, 20, 30, 40);
            notificaciones.add(new Notificacion("2017-02-25","21:25","Vacuna muy bien",6));
        }

    public ArrayList<Corral> getCorrales() {
        return corrales;
    }

    public void setCorrales(ArrayList<Corral> corrales) {
        this.corrales = corrales;
    }

    public Despensa getDespensa() {
        return despensa;
    }

    public void setDespensa(Despensa despensa) {
        this.despensa = despensa;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Gastos getGastos() {
        return gastos;
    }

    public void setGastos(Gastos gastos) {
        this.gastos = gastos;
    }


    public ArrayList<Animal> getVacas() {
        return vacas;
    }

    public void setVacas(ArrayList<Animal> vacas) {
        this.vacas = vacas;
    }

    public ArrayList<Animal> getCerdos() {
        return cerdos;
    }

    public void setCerdos(ArrayList<Animal> cerdos) {
        this.cerdos = cerdos;
    }

    public ArrayList<Animal> getGallinas() {
        return gallinas;
    }

    public void setGallinas(ArrayList<Animal> gallinas) {
        this.gallinas = gallinas;
    }

    public ArrayList<Animal> getOvejas() {
        return ovejas;
    }

    public void setOvejas(ArrayList<Animal> ovejas) {
        this.ovejas = ovejas;
    }

    public ArrayList<Animal> getCabras() {
        return cabras;
    }

    public void setCabras(ArrayList<Animal> cabras) {
        this.cabras = cabras;
    }

    public ArrayList<Animal> getOtros() {
        return otros;
    }

    public void setOtros(ArrayList<Animal> otros) {
        this.otros = otros;
    }

    public void addCoraal(Corral corral){
        this.corrales.add(corral);
    }

    @Override
    public String toString() {
        return "MiGranja{" +
                "\n corrales=" + corrales +
                ",\n despensa=" + despensa +
                ", \n veterinario=" + veterinario +
                ", \n gastos=" + gastos +
                ", \n vacas=" + vacas +
                ", \n cerdos=" + cerdos +
                ", \n gallinas=" + gallinas +
                ", \n ovejas=" + ovejas +
                ", \n cabras=" + cabras +
                ", \n otros=" + otros +
                '}';
    }

    public ArrayList<Animal> getAnimales(){

        ArrayList<Animal>  aux = vacas;
        aux.addAll(cerdos);
        aux.addAll(gallinas);
        aux.addAll(ovejas);
        aux.addAll(cabras);
        aux.addAll(otros);
        return aux;

    }

    public void addVaca(Animal animal){

        this.vacas.add(animal);
    }
    public void addGallina(Animal animal){

        this.gallinas.add(animal);
    }
    public void addCerdos(Animal animal){

        this.cerdos.add(animal);
    }
    public void addOveja(Animal animal){

        this.ovejas.add(animal);
    }
    public void addCabras(Animal animal){

        this.cabras.add(animal);
    }
    public void addOtros(Animal animal){

        this.otros.add(animal);
    }

    public void deleteVaca(String id){
        for(int i = 0; i< vacas.size();i++){
            try {
                if (vacas.get(i).getCodExplotacion().equals(id)) {
                    vacas.remove(i);
                } else { throw  new IndexOutOfBoundsException();
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void deleteCerdo(String id){
        for(int i = 0; i< cerdos.size();i++){
            try {
                if (cerdos.get(i).getCodExplotacion().equals(id)) {
                    cerdos.remove(i);
                } else { throw  new IndexOutOfBoundsException();
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void deleteGallinas(String id){
        for(int i = 0; i<gallinas.size();i++){
            try {
                if (gallinas.get(i).getCodExplotacion().equals(id)) {
                    gallinas.remove(i);
                } else { throw  new IndexOutOfBoundsException();
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void deleteOveja(String id){
        for(int i = 0; i< ovejas.size();i++){
            try {
                if (ovejas.get(i).getCodExplotacion().equals(id)) {
                    ovejas.remove(i);
                } else { throw  new IndexOutOfBoundsException();
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void deleteCabras(String id){
        for(int i = 0; i< cabras.size();i++){
            try {
                if (cabras.get(i).getCodExplotacion().equals(id)) {
                    cabras.remove(i);
                } else { throw  new IndexOutOfBoundsException();
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void deleteOtros(String id){
        for(int i = 0; i< otros.size();i++){
            try {
                if (otros.get(i).getCodExplotacion().equals(id)) {
                    otros.remove(i);
                } else { throw  new IndexOutOfBoundsException();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void deleteAnimal(int tipo, String id){

        switch (tipo){
            case 1: this.deleteVaca(id);break;
            case 2: this.deleteCerdo(id);break;
            case 3: this.deleteGallinas(id);break;
            case 4: this.deleteOveja(id);break;
            case 5: this.deleteCabras(id); ;break;
            case 6: this.deleteOtros(id);break;
        }
    }
    public void addAnimal(int tipo, Animal animal){

        switch (tipo){
            case 1: this.addVaca(animal);break;
            case 2: this.addCerdos(animal);break;
            case 3: this.addGallina(animal);break;
            case 4: this.addOveja(animal);break;
            case 5: this.addCabras(animal); ;break;
            case 6: this.addOtros(animal);break;
        }
    }

    public ArrayList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(ArrayList<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public void addNotificacion(Notificacion notificacion){
        this.notificaciones.add(notificacion);
    }

    public ArrayList<Notificacion> darNotificacion(int tipo){
        ArrayList<Notificacion> aux = new ArrayList<>();
        for (int i = 0; i< notificaciones.size(); i++){
            if(notificaciones.get(i).getTipo() == tipo)
                aux.add(notificaciones.get(i));
        }
        return aux;
    }

    public void deleteNotificacion(Notificacion notificacion){
        for (int i = 0; i< notificaciones.size(); i++){
            if(notificaciones.get(i).getTipo() == notificacion.getTipo() && notificaciones.get(i).getFecha().equals(notificacion.getFecha())
                    && notificaciones.get(i).getHora().equals(notificacion.getHora()))
                notificaciones.remove(i);
        }
    }

    public ArrayList<Vacunas> proximas (){
        ArrayList<Vacunas> aux = new ArrayList<Vacunas>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String factual = formato.format(date);
        for(int i = 0 ; i < veterinario.getVacunas().size() ; i++) {
            Date fecha = null;
            String strFecha = veterinario.getVacunas().get(i).getFecha();
            try {

                fecha = dateFormat.parse(strFecha);
                Date actual = dateFormat.parse(factual);
                if(fecha.compareTo(actual) >= 0)
                    aux.add(veterinario.getVacunas().get(i));

            } catch (ParseException ex) {

                ex.printStackTrace();

            }

        }
        return aux;
    }


}
