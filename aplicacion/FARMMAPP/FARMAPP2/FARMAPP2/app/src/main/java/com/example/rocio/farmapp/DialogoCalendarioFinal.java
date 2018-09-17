package com.example.rocio.farmapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Rocio on 22/9/17.
 */

public  class DialogoCalendarioFinal extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;
    private String fechainicio;

    public static DialogoCalendarioFinal newInstance(DatePickerDialog.OnDateSetListener listener,String fechainicio) {
        DialogoCalendarioFinal fragment = new DialogoCalendarioFinal();
        fragment.setListener(listener);
        fragment.setfechainicio(fechainicio);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    public void setfechainicio(String fechainicio){
        this.fechainicio = fechainicio;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        if(!fechainicio.equals("")) {
            String[] fecha = fechainicio.split("-");
            System.out.println(fecha[1]);
            c.set(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1])-1, Integer.parseInt(fecha[2]));
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),listener,year,month,day);
        dpd.getDatePicker().setMinDate(c.getTimeInMillis());


        // Create a new instance of DatePickerDialog and return it
        //return new DatePickerDialog(getActivity(), listener, year, month, day);
        return dpd;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
    }

}
