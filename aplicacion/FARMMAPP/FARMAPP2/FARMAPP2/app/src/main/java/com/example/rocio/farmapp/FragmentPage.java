package com.example.rocio.farmapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentPage extends android.support.v4.app.Fragment {

    private final int mRequestCode = 100;
    private MiGranja miGranja = null;
    private int tipoanimal;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        final Animal animal = (Animal) bundle.getSerializable("animal");
        miGranja = (MiGranja) bundle.getSerializable("miGranja");
        tipoanimal = bundle.getInt("tipoanimal");

        view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        TextView textView = (TextView) view.findViewById(R.id.pageNumber);
        textView.setText(Integer.toString(pageNumber));
        ImageView foto = (ImageView) view.findViewById(R.id.imagen);
        EditText codigo = (EditText) view.findViewById(R.id.codigo);
        codigo.setText(animal.getCodExplotacion());
        EditText corral = (EditText) view.findViewById(R.id.corral);
        Button detalles = (Button) view.findViewById(R.id.detalles);
        detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(detallesanimal.getCallingIntent(getContext(), miGranja, animal, tipoanimal), mRequestCode);



            }
        });


        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }



}
