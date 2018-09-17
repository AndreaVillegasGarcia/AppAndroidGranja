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

public class plagas extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Dialog dialogoprogramarinsecto;
    private Dialog dialogoprogramarrata;
    private TextView hora;
    private MiGranja miGranja;
    private Corral corral;
    private int mRequestCode = 100;
    private int posicionarray;
    private TextView fechadesinsectacion;
    private TextView fechadesratizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plagas);
        dialogoprogramarinsecto = new Dialog(this);
        dialogoprogramarrata = new Dialog(this);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");
        corral = (Corral)b.getSerializable("corral");
        posicionarray = b.getInt("posicionarray");

        fechadesinsectacion = (TextView) findViewById(R.id.insectofecha);
        fechadesratizacion = (TextView) findViewById(R.id.ratafecha);
        fechadesinsectacion.setText(corral.getPlagas().get(corral.getPlagas().size()-1).GetFechaPlaga());
        fechadesratizacion.setText(corral.getDesratizacion().get(corral.getDesratizacion().size()-1).GetFechaPlaga());



        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }


    public void anyadirdesinsectación(View v){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fechas = dateFormat.format(date);
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String horas = hourFormat.format(date);

        Plaga aux = new Plaga(fechas,horas,0);
        fechadesinsectacion.setText(aux.GetFechaPlaga());
        miGranja.getCorrales().get(posicionarray).addPlaga(aux);
        corral = miGranja.getCorrales().get(posicionarray);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Desinsectación añadida con éxito");
        alerta.setMessage("La desinsecación ha sido añadida con éxito");
        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                dialog.dismiss();
            }
        });

        AlertDialog alert = alerta.create();
        alert.show();
    }

    public void anyadirdesratizacion(View v){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fechas = dateFormat.format(date);
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String horas = hourFormat.format(date);

        Plaga aux = new Plaga(fechas,horas,1);
        fechadesratizacion.setText(aux.GetFechaPlaga());
        miGranja.getCorrales().get(posicionarray).addPlaga(aux);
        corral = miGranja.getCorrales().get(posicionarray);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Desratización añadida con éxito");
        alerta.setMessage("La desratización ha sido añadida con éxito");
        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                dialog.dismiss();
            }
        });

        AlertDialog alert = alerta.create();
        alert.show();
    }

    public void programardesinsectación(View v){
        ImageButton calendario;
        ImageButton horapic;
        final TextView fecha;
        Button aceptar;
        Button cancelar;
        dialogoprogramarinsecto.setContentView(R.layout.programardesinsectacion);
        fecha = (TextView) dialogoprogramarinsecto.findViewById(R.id.fecha);
        hora = (TextView) dialogoprogramarinsecto.findViewById(R.id.hora);
        calendario = (ImageButton) dialogoprogramarinsecto.findViewById(R.id.calendario);
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

        horapic = (ImageButton) dialogoprogramarinsecto.findViewById(R.id.horapic);
        horapic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogotimer();
            }
        });

        aceptar = (Button) dialogoprogramarinsecto.findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fecha.getText().toString().equals("") || hora.getText().toString().equals("")){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramarinsecto.getContext());
                    alerta.setTitle("No se puedo programar la desinsectación");
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
                    String mensaje = "Programación de desinsectación en el corral: " + posicionarray;
                    Notificacion auxiliar = new Notificacion(fecha.getText().toString(),hora.getText().toString(),mensaje,2);
                    miGranja.addNotificacion(auxiliar);
                    AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramarinsecto.getContext());
                    alerta.setTitle("Desinsectación programada con éxito");
                    alerta.setMessage("La desinsectación fue programada con éxito, para consultar todas las programaciones por favor " +
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
                dialogoprogramarinsecto.dismiss();

            }
        });

        cancelar = (Button) dialogoprogramarinsecto.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoprogramarinsecto.dismiss();
            }
        });

        dialogoprogramarinsecto.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogoprogramarinsecto.show();

    }

    public void programardesratizacion(View v){
        ImageButton calendario;
        ImageButton horapic;
        final TextView fecha;
        Button aceptar;
        Button cancelar;
        dialogoprogramarrata.setContentView(R.layout.programardesratizacion);
        fecha = (TextView) dialogoprogramarrata.findViewById(R.id.fecha);
        hora = (TextView) dialogoprogramarrata.findViewById(R.id.hora);
        calendario = (ImageButton) dialogoprogramarrata.findViewById(R.id.calendario);
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

        horapic = (ImageButton) dialogoprogramarrata.findViewById(R.id.horapic);
        horapic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogotimer();
            }
        });

        aceptar = (Button) dialogoprogramarrata.findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fecha.getText().toString().equals("") || hora.getText().toString().equals("")){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramarrata.getContext());
                    alerta.setTitle("No se puedo programar la desratización");
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
                    String mensaje = "Programación de desratización en el corral: " + posicionarray;
                    Notificacion auxiliar = new Notificacion(fecha.getText().toString(),hora.getText().toString(),mensaje,3);
                    miGranja.addNotificacion(auxiliar);
                    AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramarrata.getContext());
                    alerta.setTitle("Desratización programada con éxito");
                    alerta.setMessage("La desratización fue programada con éxito, para consultar todas las programaciones por favor " +
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
                dialogoprogramarrata.dismiss();

            }
        });

        cancelar = (Button) dialogoprogramarrata.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoprogramarrata.dismiss();
            }
        });

        dialogoprogramarrata.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogoprogramarrata.show();
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
    public static Intent getCallingIntent(Context context, MiGranja miGranja, Corral corral, int posicionarray){

        Intent intent = new Intent(context, plagas.class );
        intent.putExtra("miGranja", miGranja);
        intent.putExtra("corral", corral);
        intent.putExtra("posicionarray",posicionarray);
        return intent;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("miGranja", miGranja);
        intent.putExtra("corral", corral);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && resultCode == RESULT_OK) {
            miGranja = (MiGranja)data.getSerializableExtra("miGranja");
            corral = (Corral)data.getSerializableExtra("corral");
        }
    }
}
