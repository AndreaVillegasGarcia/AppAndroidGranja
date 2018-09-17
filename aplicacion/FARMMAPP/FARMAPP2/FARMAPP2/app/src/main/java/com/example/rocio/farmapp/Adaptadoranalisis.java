package com.example.rocio.farmapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptadoranalisis extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    private MiGranja miGranja;
    private Corral corral;
    private int posicionarray;
    private ArrayList<AnalisisAgua> analisis;

    public Adaptadoranalisis(Context contexto,MiGranja miGranja, Corral corral, int posicionarray){

        this.contexto = contexto;
        this.miGranja = miGranja;
        this.corral = corral;
        this.posicionarray = posicionarray;
        analisis = new ArrayList<AnalisisAgua>();
        analisis = corral.getAnalisisAguas();
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final View vista = inflater.inflate(R.layout.rowanalisis, null);
        TextView idAnalisis = (TextView) vista.findViewById(R.id.idanalisis);
        TextView fecha = (TextView) vista.findViewById(R.id.fecha);
        TextView estado = (TextView) vista.findViewById(R.id.estado);
        TextView composicion = (TextView) vista.findViewById(R.id.composicion);

        idAnalisis.setText(Integer.toString(analisis.get(position).getIdAnalisis()));
        fecha.setText(analisis.get(position).getFecha());
        estado.setText(analisis.get(position).getEstadoAnalisis());
        composicion.setText(analisis.get(position).getComposción());

        return vista;
    }


    @Override
    public int getCount() {
        return analisis.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
