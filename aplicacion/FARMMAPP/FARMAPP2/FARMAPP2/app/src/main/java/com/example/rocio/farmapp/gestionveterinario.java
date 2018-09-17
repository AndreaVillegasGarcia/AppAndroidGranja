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

import java.util.Calendar;

public class gestionveterinario extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private MiGranja miGranja;
    private int mRequestCode;
    private Dialog dialogoprogramaranalisis;
    private TextView hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionveterinario);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");
        dialogoprogramaranalisis = new Dialog(this);

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    public void ultimasvacunas(View v){
        startActivity(ultimasvacunas.getCallingIntent(this, miGranja));
    }  //OK
    public void proximasvacunas(View v){
        startActivity(ultimasvacunas.getCallingIntent(this, miGranja));
    } //OK
    public void programarvacunas(View v){
        ImageButton calendario;
        ImageButton horapic;
        final TextView fecha;
        Button aceptar;
        Button cancelar;
        dialogoprogramaranalisis.setContentView(R.layout.dialogoprogramarvacuna);
        fecha = (TextView) dialogoprogramaranalisis.findViewById(R.id.fecha);
        hora = (TextView) dialogoprogramaranalisis.findViewById(R.id.hora);
        calendario = (ImageButton) dialogoprogramaranalisis.findViewById(R.id.calendario);
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

        horapic = (ImageButton) dialogoprogramaranalisis.findViewById(R.id.horapic);
        horapic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogotimer();
            }
        });

        aceptar = (Button) dialogoprogramaranalisis.findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fecha.getText().toString().equals("") || hora.getText().toString().equals("")){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramaranalisis.getContext());
                    alerta.setTitle("No se puedo programar el análisis");
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
                    String mensaje = "Programación de vacunas " ;
                    Notificacion auxiliar = new Notificacion(fecha.getText().toString(),hora.getText().toString(),mensaje,6);
                    miGranja.addNotificacion(auxiliar);
                    Vacunas aux = new Vacunas(miGranja.getVeterinario().getVacunas().size(),miGranja.getCabras().get(0), fecha.getText().toString());
                    miGranja.getVeterinario().addVacuna(aux);
                    AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramaranalisis.getContext());
                    alerta.setTitle("Vacuna programada con éxito");
                    alerta.setMessage("La vacuna fue programada con éxito, para consultar todas las programaciones por favor " +
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
                dialogoprogramaranalisis.dismiss();

            }
        });

        cancelar = (Button) dialogoprogramaranalisis.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoprogramaranalisis.dismiss();
            }
        });

        dialogoprogramaranalisis.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogoprogramaranalisis.show();

    } //OK
    public void historialnacimientos(View v){

        startActivity(historialnacimientos.getCallingIntent(this, miGranja));;

    } // OK
    public void gestacion(View v){
        startActivity(gestiongestacion.getCallingIntent(this, miGranja));
    } //OK

    public static Intent getCallingIntent(Context context, MiGranja miGranja){

        Intent intent = new Intent(context, gestionveterinario.class );
        intent.putExtra("miGranja", miGranja);
        return intent;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && resultCode == RESULT_OK) {
            miGranja = (MiGranja)data.getSerializableExtra("miGranja");
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("miGranja", miGranja);
        setResult(RESULT_OK, intent);
        finish();
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
}
