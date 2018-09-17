package com.example.rocio.farmapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adaptadornacimientos extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    private MiGranja miGranja;

    public Adaptadornacimientos(Context contexto, MiGranja miGranja){

        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.miGranja = miGranja;
    }

    @Override
    public int getCount() {
        return miGranja.getVeterinario().getNacimientos().size();
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

        final View vista = inflater.inflate(R.layout.rownacimientos, null);
        TextView corral = (TextView) vista.findViewById(R.id.corral);
        TextView fecha = (TextView) vista.findViewById(R.id.fecha);
        TextView idmadre = (TextView) vista.findViewById(R.id.idmadre);
        TextView raza = (TextView) vista.findViewById(R.id.raza);

        Nacimientos auxiliar = miGranja.getVeterinario().getNacimientos().get(i);
        corral.setText(String.valueOf(auxiliar.getCorral()));
        fecha.setText(auxiliar.getFecha());
        idmadre.setText(auxiliar.getIdMadre());
        raza.setText(auxiliar.getRaza());
        return vista;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
