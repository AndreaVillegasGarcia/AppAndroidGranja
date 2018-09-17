package com.example.rocio.farmapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SwipeAdapterverges extends FragmentStatePagerAdapter {
    private MiGranja miGranja;
    private ArrayList<Animal> animales;
    private  int options;

    public SwipeAdapterverges(FragmentManager fr) {
        super(fr);
        miGranja = null;
        animales = null;
        options = -1;
    }

    public SwipeAdapterverges(FragmentManager fr, MiGranja miGranja, ArrayList<Animal> animales,int options) {
        super(fr);
        this.miGranja = miGranja;
        this.animales = animales;
        this.options = options;

    }



    @Override
    public Fragment getItem(int position) {
        FragmentPageverges pageFragment = new FragmentPageverges();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber",position+1);
        bundle.putSerializable("animal", animales.get(position));
        bundle.putSerializable("miGranja", miGranja);
        bundle.putInt("options",options);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return animales.size();
    }
}
