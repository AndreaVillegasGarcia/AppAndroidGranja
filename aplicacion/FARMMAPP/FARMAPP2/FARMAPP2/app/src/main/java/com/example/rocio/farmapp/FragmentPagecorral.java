package com.example.rocio.farmapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentPagecorral extends android.support.v4.app.Fragment{

    private final int mRequestCode = 100;
    private MiGranja miGranja = null;
    private int tipoanimal;
    private int posicionarray;
    private int opcion;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        final Animal animal = (Animal) bundle.getSerializable("animal");
        miGranja = (MiGranja) bundle.getSerializable("miGranja");
        tipoanimal = bundle.getInt("tipoanimal");
        posicionarray = bundle.getInt("corral");
        opcion = bundle.getInt("opcion");

        view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        TextView textView = (TextView) view.findViewById(R.id.pageNumber);
        textView.setText(Integer.toString(pageNumber));
        ImageView foto = (ImageView) view.findViewById(R.id.imagen);
        EditText codigo = (EditText) view.findViewById(R.id.codigo);
        codigo.setText(animal.getCodExplotacion());
        final EditText corral = (EditText) view.findViewById(R.id.corral);
        corral.setText(Integer.toString(posicionarray));
       final Button detalles = (Button) view.findViewById(R.id.detalles);
       if(opcion == 1) detalles.setText("Eliminar animal");
        detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(opcion == 0)
                startActivityForResult(detallesanimal.getCallingIntent(getContext(), miGranja, animal, tipoanimal), mRequestCode);
                else if(opcion == 1){
                    AlertDialog.Builder confirmacion = new AlertDialog.Builder(getContext());

                    confirmacion.setTitle("Eliminar animal");
                    confirmacion.setMessage("¿Estás seguro que deseas eliminar este animal?");
                    confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog

                            miGranja.deleteAnimal(tipoanimal,animal.getCodExplotacion());


                            dialog.dismiss();
                            startActivity(gestionocupacion.getCallingIntent(getContext(), miGranja,miGranja.getCorrales().get(posicionarray),posicionarray));
                        }
                    });

                    confirmacion.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                            startActivity(gestionocupacion.getCallingIntent(getContext(), miGranja,miGranja.getCorrales().get(posicionarray),posicionarray));
                        }
                    });
                    AlertDialog alert =confirmacion.create();
                    alert.show();
                }



            }
        });


        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }



}