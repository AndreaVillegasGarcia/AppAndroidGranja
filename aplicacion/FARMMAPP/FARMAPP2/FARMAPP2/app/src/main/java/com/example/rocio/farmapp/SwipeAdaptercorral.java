package com.example.rocio.farmapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SwipeAdaptercorral extends FragmentStatePagerAdapter {
    private MiGranja miGranja;
    private ArrayList<Corral> corrales;

    public SwipeAdaptercorral(FragmentManager fr) {
        super(fr);
        miGranja = null;
        corrales = null;
    }
    public SwipeAdaptercorral(FragmentManager fr, MiGranja miGranja) {
        super(fr);
        this.miGranja = miGranja;
        corrales = new ArrayList<Corral>();
        corrales = miGranja.getCorrales();
    }
    @Override
    public Fragment getItem(int position) {
        Fragment pageFragmentCorral = new PageFragmentCorral();
        Corral corral = corrales.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber",position);
        bundle.putSerializable("miGranja", miGranja);
        bundle.putSerializable("corral",corral);
        pageFragmentCorral.setArguments(bundle);
        return pageFragmentCorral;
    }

    @Override
    public int getCount() {
        return corrales.size();
    }
}
