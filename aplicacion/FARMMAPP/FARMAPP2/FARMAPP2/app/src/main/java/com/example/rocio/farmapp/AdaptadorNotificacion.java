package com.example.rocio.farmapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorNotificacion extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context contexto;
    private MiGranja miGranja;

    public AdaptadorNotificacion(Context contexto, MiGranja miGranja){

        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.miGranja = miGranja;
    }

    @Override
    public int getCount() {
        return miGranja.getNotificaciones().size();
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

        final View vista = inflater.inflate(R.layout.rownotificacion, null);
        TextView tipo = (TextView) vista.findViewById(R.id.tipo);
        TextView fecha = (TextView) vista.findViewById(R.id.fecha);
        TextView hora = (TextView) vista.findViewById(R.id.hora);
        TextView mensaje = (TextView) vista.findViewById(R.id.mensaje);

        Notificacion auxiliar = miGranja.getNotificaciones().get(i);
        switch (auxiliar.getTipo()){
            case 0:
                tipo.setText("Desinfeción");
                break;
            case 1:
                tipo.setText("Limpieza");
                break;
            case 2:
                tipo.setText("Desinsectación");
                break;
            case 3:
                tipo.setText("Desratización");
                break;
            case 4:
                tipo.setText("Pienso");
                break;
            case 5:
                tipo.setText("Análisis");
                break;
            case 6:
                tipo.setText("Vacuna");
                break;
        }
        fecha.setText(auxiliar.getFecha());
        hora.setText(auxiliar.getHora());
        mensaje.setText(auxiliar.getMensaje());
        return vista;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
