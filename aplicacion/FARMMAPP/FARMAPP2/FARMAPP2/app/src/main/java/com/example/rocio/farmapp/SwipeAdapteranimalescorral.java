package com.example.rocio.farmapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SwipeAdapteranimalescorral extends FragmentStatePagerAdapter {
    private MiGranja miGranja;
    private int tipoanimal;
    private ArrayList<Animal> animales;
    private Corral corral;
    private int posicionarray;
    private int opcion;

    public SwipeAdapteranimalescorral(FragmentManager fr) {
        super(fr);
        miGranja = null;
        tipoanimal = -1;
        animales = null;
        posicionarray = -1;
        opcion = -1;
    }

    public SwipeAdapteranimalescorral(FragmentManager fr, MiGranja miGranja,Corral corral, int tipoanimal, int posicionarray, int opcion) {
        super(fr);
        this.miGranja = miGranja;
        this.tipoanimal = tipoanimal;
        this.corral = corral;
        this.posicionarray = posicionarray;
        animales = new ArrayList<Animal>();
        this.opcion = opcion;

        seleccion();

    }

    public void seleccion(){
        animales = miGranja.getCorrales().get(posicionarray).getAnimal();
    }


    @Override
    public Fragment getItem(int position) {
        FragmentPagecorral pageFragment = new FragmentPagecorral();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber",position+1);
        bundle.putSerializable("animal", animales.get(position));
        bundle.putSerializable("miGranja", miGranja);
        bundle.putInt("tipoanimal", tipoanimal);
        bundle.putInt("corral", posicionarray);
        bundle.putInt("opcion",opcion);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return animales.size();
    }
}
