package com.example.rocio.farmapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    private MiGranja miGranja;
    private int tipoanimal;
    private ArrayList<Animal> animales;

    public SwipeAdapter(FragmentManager fr) {
        super(fr);
        miGranja = null;
        tipoanimal = -1;
        animales = null;
    }

    public SwipeAdapter(FragmentManager fr, MiGranja miGranja, int tipoanimal) {
        super(fr);
        this.miGranja = miGranja;
        this.tipoanimal = tipoanimal;
        animales = new ArrayList<Animal>();
        seleccion();

    }

    public void seleccion(){
        switch (tipoanimal) {
            case 0:
                animales = miGranja.getVacas();
                break;
            case 1:
                animales = miGranja.getCerdos();
                break;
            case 2:
                animales = miGranja.getGallinas();
                break;
            case 3:
                animales = miGranja.getOvejas();
                break;
            case 4:
                animales = miGranja.getCabras();
                break;
            case 5:
                animales = miGranja.getOtros();
                break;
        }
    }


    @Override
    public Fragment getItem(int position) {
        FragmentPage pageFragment = new FragmentPage();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber",position+1);
        bundle.putSerializable("animal", animales.get(position));
        bundle.putSerializable("miGranja", miGranja);
        bundle.putInt("tipoanimal", tipoanimal);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return animales.size();
    }
}
