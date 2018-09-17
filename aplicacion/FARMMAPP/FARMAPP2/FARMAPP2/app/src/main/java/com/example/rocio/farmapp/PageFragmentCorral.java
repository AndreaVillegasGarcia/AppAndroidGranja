package com.example.rocio.farmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PageFragmentCorral extends android.support.v4.app.Fragment {

    private MiGranja miGranja = null;
    private Corral corral;
    private int posicionarray;
    private int mRequestCode = 100;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        miGranja = (MiGranja) bundle.getSerializable("miGranja");
        corral = (Corral) bundle.getSerializable("corral");
        posicionarray = bundle.getInt("pageNumber");

        view = inflater.inflate(R.layout.page_fragment_corral,container,false);
        TextView textView = (TextView) view.findViewById(R.id.numerotext);
        TextView textView2 = (TextView) view.findViewById(R.id.ocupaciontext);
        TextView textView3 = (TextView) view.findViewById(R.id.razatext);
        TextView textView4 = (TextView) view.findViewById(R.id.localizaciontext);
        EditText numero = (EditText) view.findViewById(R.id.numero);
        EditText ocupacion= (EditText) view.findViewById(R.id.ocupacion);
        EditText raza = (EditText) view.findViewById(R.id.raza);
        EditText localizacion = (EditText) view.findViewById(R.id.localizacion);
        Button detalles = (Button) view.findViewById(R.id.detalles);

        numero.setText(Integer.toString(corral.getNumCorral()));
        ocupacion.setText(Integer.toString(corral.getAnimal().size()));
        raza.setText(corral.getAnimal().get(0).getRaza());
        localizacion.setText(corral.getLocalizacion());

        detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(gestioninstalaciones.getCallingIntent(getContext(), miGranja, corral,posicionarray), mRequestCode);
            }
        });


        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
