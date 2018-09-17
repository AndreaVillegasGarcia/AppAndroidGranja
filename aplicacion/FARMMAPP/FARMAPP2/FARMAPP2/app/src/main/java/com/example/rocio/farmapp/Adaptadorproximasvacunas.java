package com.example.rocio.farmapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adaptadorproximasvacunas extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;

    public Adaptadorproximasvacunas(Context contexto){

        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.rowpienso, null);
        TextView nombre = (TextView) vista.findViewById(R.id.nombre);
        TextView fecha = (TextView) vista.findViewById(R.id.fecha);
        TextView idanimal = (TextView) vista.findViewById(R.id.idanimal);
        TextView raza = (TextView) vista.findViewById(R.id.raza);
        return null;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
