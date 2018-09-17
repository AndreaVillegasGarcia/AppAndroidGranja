package com.example.rocio.farmapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorUltimasvacunas extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    private MiGranja miGranja;

    public AdaptadorUltimasvacunas(Context contexto, MiGranja miGranja){

        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.miGranja = miGranja;
    }


    @Override
    public int getCount() {
        return miGranja.getVeterinario().getVacunas().size();
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
        final View vista = inflater.inflate(R.layout.rowultimasvacunas, null);
        TextView nombre = (TextView) vista.findViewById(R.id.nombre);
        TextView fecha = (TextView) vista.findViewById(R.id.fecha);
        TextView idanimal = (TextView) vista.findViewById(R.id.idanimal);
        TextView raza = (TextView) vista.findViewById(R.id.raza);

        Vacunas auxiliar = miGranja.getVeterinario().getVacunas().get(i);
        nombre.setText(String.valueOf(auxiliar.getIdVacuna()));
        fecha.setText(auxiliar.getFecha());
        idanimal.setText(auxiliar.getAnimal().getCodExplotacion());
        raza.setText(auxiliar.getAnimal().getRaza());

        return vista;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
