package com.example.rocio.farmapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adaptadorpienso extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    private MiGranja miGranja;
    private Corral corral;

    public Adaptadorpienso(Context contexto, MiGranja miGranja, Corral corral){

        this.contexto = contexto;
        this.miGranja = miGranja;
        this.corral = corral;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return corral.getDispensaciones().size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.rowpienso, null);
        TextView hora = (TextView) vista.findViewById(R.id.hora);
        TextView fecha = (TextView) vista.findViewById(R.id.fecha);
        TextView cantidad = (TextView) vista.findViewById(R.id.cantidad);
        TextView composicion = (TextView) vista.findViewById(R.id.composicion);
        Dispensacion auxiliar = corral.getDispensaciones().get(position);

        hora.setText(auxiliar.getHora());
        fecha.setText(auxiliar.getFecha());
        cantidad.setText(Float.toString(auxiliar.getCantidad()));
        composicion.setText(auxiliar.getComposicion());

        return vista;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
