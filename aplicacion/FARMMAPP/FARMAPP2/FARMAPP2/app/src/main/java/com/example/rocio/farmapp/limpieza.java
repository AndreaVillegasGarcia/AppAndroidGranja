package com.example.rocio.farmapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class limpieza extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Dialog dialogoprogramarlimpieza;
    private Dialog dialogoprogramardesinfeccion;
    private String texto;
    private TextView hora;
    private MiGranja miGranja;
    private Corral corral;
    private int mRequestCode = 100;
    private TextView fechalimpieza;
    private TextView fechadesinfeccion;
    private int posicionarray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limpieza);
        dialogoprogramarlimpieza = new Dialog(this);
        dialogoprogramardesinfeccion = new Dialog(this);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");
        corral = (Corral)b.getSerializable("corral");
        posicionarray = b.getInt("posicionarray");

        fechalimpieza = (TextView) findViewById(R.id.limpiezafecha);
        fechadesinfeccion = (TextView) findViewById(R.id.desinfeccionfecha);

        fechalimpieza.setText(corral.getLimpiezas().get(corral.getLimpiezas().size()-1).getFecha());
        fechadesinfeccion.setText(corral.getDesinfecciones().get(corral.getDesinfecciones().size()-1).getFecha());

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    public void anyadirdesinfeccion(View w){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fechas = dateFormat.format(date);
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String horas = hourFormat.format(date);

        Limpiezas aux = new Limpiezas(fechas,horas,1);
        fechadesinfeccion.setText(aux.getFecha());
        miGranja.getCorrales().get(posicionarray).addLimpieza(aux);
        corral = miGranja.getCorrales().get(posicionarray);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Desinfección añadida con éxito");
        alerta.setMessage("La desinfección ha sido añadida con éxito");
        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                dialog.dismiss();
            }
        });

        AlertDialog alert = alerta.create();
        alert.show();
    }
    public void anyadirlimpieza(View w){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fechas = dateFormat.format(date);
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String horas = hourFormat.format(date);

        Limpiezas aux = new Limpiezas(fechas,horas,0);
        fechalimpieza.setText(aux.getFecha());
        miGranja.getCorrales().get(posicionarray).addLimpieza(aux);
        corral = miGranja.getCorrales().get(posicionarray);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Limpieza añadida con éxito");
        alerta.setMessage("La limpieza ha sido añadida con éxito");
        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                dialog.dismiss();
            }
        });

        AlertDialog alert = alerta.create();
        alert.show();


    }
    public void programardesinfeccion(View w){
        ImageButton calendario;
        final ImageButton horapic;
        final TextView fecha;
        Button aceptar;
        Button cancelar;
        dialogoprogramardesinfeccion.setContentView(R.layout.programardesinfeccion);
        fecha = (TextView) dialogoprogramardesinfeccion.findViewById(R.id.fecha);
        hora = (TextView) dialogoprogramardesinfeccion.findViewById(R.id.hora);
        calendario = (ImageButton) dialogoprogramardesinfeccion.findViewById(R.id.calendario);
        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoCalendario newFragment = DialogoCalendario.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 because january is zero
                        String selectedDate ="";
                        if(month < 9)
                            selectedDate = year + "-0" + (month+1);
                        else
                            selectedDate = year + "-" + (month+1);
                        if(day <10)
                            selectedDate += "-0" + day;
                        else
                            selectedDate +=  "-" + day;
                        final String date = selectedDate;
                        fecha.setText(date);
                    }
                });
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        horapic = (ImageButton) dialogoprogramardesinfeccion.findViewById(R.id.horapic);
        horapic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogotimer();
            }
        });

        aceptar = (Button) dialogoprogramardesinfeccion.findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fecha.getText().toString().equals("") || hora.getText().toString().equals("")){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramardesinfeccion.getContext());
                    alerta.setTitle("No se puedo programar la desinfección");
                    alerta.setMessage("Alguno de los campos está vacío");
                    alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog

                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = alerta.create();
                    alert.show();
                }
                else
                {
                    String mensaje = "Programación de desinfectación en el corral: " + posicionarray;
                    Notificacion auxiliar = new Notificacion(fecha.getText().toString(),hora.getText().toString(),mensaje,0);
                    miGranja.addNotificacion(auxiliar);
                    AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramardesinfeccion.getContext());
                    alerta.setTitle("Desinfección programada con éxito");
                    alerta.setMessage("La desinfección fue programada con éxito, para consultar todas las programaciones por favor " +
                            "acceda al apartado de Notificaciones");
                    alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog

                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = alerta.create();
                    alert.show();
                }
                dialogoprogramardesinfeccion.dismiss();

            }

        });

        cancelar = (Button) dialogoprogramardesinfeccion.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoprogramardesinfeccion.dismiss();
            }
        });

        dialogoprogramardesinfeccion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogoprogramardesinfeccion.show();
    }
    public void programarlimpieza(View w){
            ImageButton calendario;
            ImageButton horapic;
            final TextView fecha;
            Button aceptar;
            Button cancelar;
        dialogoprogramarlimpieza.setContentView(R.layout.programarlimpieza);
            fecha = (TextView) dialogoprogramarlimpieza.findViewById(R.id.fecha);
            hora = (TextView) dialogoprogramarlimpieza.findViewById(R.id.hora);
            calendario = (ImageButton) dialogoprogramarlimpieza.findViewById(R.id.calendario);
            calendario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogoCalendario newFragment = DialogoCalendario.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            // +1 because january is zero
                            String selectedDate ="";
                            if(month < 9)
                                selectedDate = year + "-0" + (month+1);
                            else
                                selectedDate = year + "-" + (month+1);
                            if(day <10)
                                selectedDate += "-0" + day;
                            else
                                selectedDate +=  "-" + day;
                            final String date = selectedDate;
                            fecha.setText(date);
                        }
                    });
                    newFragment.show(getFragmentManager(), "datePicker");
                }
            });

            horapic = (ImageButton) dialogoprogramarlimpieza.findViewById(R.id.horapic);
            horapic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialogotimer();
                }
            });

            aceptar = (Button) dialogoprogramarlimpieza.findViewById(R.id.aceptar);
            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(fecha.getText().toString().equals("") || hora.getText().toString().equals("")){
                        AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramarlimpieza.getContext());
                        alerta.setTitle("No se puedo programar la limpieza");
                        alerta.setMessage("Alguno de los campos está vacío");
                        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing but close the dialog

                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = alerta.create();
                        alert.show();
                    }
                    else
                    {
                        String mensaje = "Programación de limpieza en el corral: " + posicionarray;
                        Notificacion auxiliar = new Notificacion(fecha.getText().toString(),hora.getText().toString(),mensaje,1);
                        miGranja.addNotificacion(auxiliar);
                        AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramarlimpieza.getContext());
                        alerta.setTitle("Limpieza programada con éxito");
                        alerta.setMessage("La limpieza fue programada con éxito, para consultar todas las programaciones por favor " +
                                "acceda al apartado de Notificaciones");
                        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing but close the dialog

                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = alerta.create();
                        alert.show();
                    }
                    dialogoprogramarlimpieza.dismiss();

                }
            });

            cancelar = (Button) dialogoprogramarlimpieza.findViewById(R.id.cancelar);
            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogoprogramarlimpieza.dismiss();
                }
            });

        dialogoprogramarlimpieza.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogoprogramarlimpieza.show();

    }


    private void dialogotimer(){

        final Calendar c = Calendar.getInstance();
        int mYear, mMonth, mDay, mHour, mMinute;
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        hora.setText(hour + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();


    }


    public void selectItemDrawer (MenuItem menuItem){
        Intent activity = null ;
        switch (menuItem.getItemId()){
            case R.id.cuenta:
                break;
            case R.id.notificaciones:
                startActivityForResult(notificaciones.getCallingIntent(this, miGranja),mRequestCode);
                break;
            case R.id.nanimales:
                startActivityForResult(animales.getCallingIntent(this, miGranja),mRequestCode);
                break;
            case R.id.nveterinario:
                startActivity(gestionveterinario.getCallingIntent(this, miGranja));
                break;
            case R.id.ncompras:
                startActivityForResult(gestioncompras.getCallingIntent(this, miGranja), mRequestCode);
                break;
            case R.id.ninstalaciones:
                startActivityForResult(visualizarcorral.getCallingIntent(this, miGranja), mRequestCode);
                break;
            case R.id.ndespensa:
                startActivityForResult(gestiondespensa.getCallingIntent(this, miGranja), mRequestCode);
                break;
            case R.id.ngastos:
                startActivityForResult(controlgastos.getCallingIntent(this, miGranja),mRequestCode);
                break;
        }
        menuItem.setChecked(true);
        //startActivity(activity);
        mDrawerLayout.closeDrawers();
    }

    private void setDrawerContent(NavigationView nview){
        nview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getCallingIntent(Context context, MiGranja miGranja, Corral corral,int posicionarray){

        Intent intent = new Intent(context, limpieza.class );
        intent.putExtra("miGranja", miGranja);
        intent.putExtra("corral", corral);
        intent.putExtra("posicionarray",posicionarray);
        return intent;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("miGranja", miGranja);
        intent.putExtra("corral",corral);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && resultCode == RESULT_OK) {
            miGranja = (MiGranja)data.getSerializableExtra("miGranja");
        }
    }
}
