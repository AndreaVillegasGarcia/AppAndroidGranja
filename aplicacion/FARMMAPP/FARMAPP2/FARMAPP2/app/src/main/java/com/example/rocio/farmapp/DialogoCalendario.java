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

public  class DialogoCalendario extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public static DialogoCalendario newInstance(DatePickerDialog.OnDateSetListener listener) {
        DialogoCalendario fragment = new DialogoCalendario();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // DatePickerDialog dpd = new DatePickerDialog(getActivity(),listener,year,month,day);
        //dpd.getDatePicker().setMinDate(c.getTimeInMillis());


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), listener, year, month, day);
        // return dpd;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
    }

}
