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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class pienso extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Dialog dialogoprogramarpienso;
    private Dialog dialogodispensacionmanual;
    private String texto;
    private TextView hora;
    private MiGranja miGranja;
    private Corral corral;
    private int mRequestCode = 100;
    private TextView composicion;
    private TextView cantidad;
    private TextView fecha;
   EditText cantidadpienso = null;
   private int posicionarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pienso);
        dialogoprogramarpienso = new Dialog(this);
        dialogodispensacionmanual = new Dialog(this);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");
        corral = (Corral)b.getSerializable("corral");
        posicionarray = b.getInt("posicionarray");

        //cargar datos
        composicion = (TextView) findViewById(R.id.composicionpienso);
        cantidad = (TextView) findViewById(R.id.cantidadpienso);
        fecha = (TextView) findViewById(R.id.dispensacionfecha);

        Dispensacion ultima = corral.getDispensaciones().get(corral.getDispensaciones().size()-1);
        composicion.setText(ultima.getComposicion());
        cantidad.setText(Float.toString(ultima.getCantidad()));
        fecha.setText(ultima.getFecha()+ " "+ ultima.getHora());

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    public void programardispensacion(View v){
            ImageButton calendario;
            ImageButton horapic;
            final TextView fecha;
            final EditText cantidad;
            Button aceptar;
            Button cancelar;
            dialogoprogramarpienso.setContentView(R.layout.programarpienso);
            fecha = (TextView) dialogoprogramarpienso.findViewById(R.id.fecha);
            hora = (TextView) dialogoprogramarpienso.findViewById(R.id.hora);
            calendario = (ImageButton) dialogoprogramarpienso.findViewById(R.id.calendario);
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

            horapic = (ImageButton) dialogoprogramarpienso.findViewById(R.id.horapic);
            horapic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialogotimer();
                }
            });

             cantidad = (EditText) dialogoprogramarpienso.findViewById(R.id.cantidad);

            aceptar = (Button) dialogoprogramarpienso.findViewById(R.id.aceptar);
            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(fecha.getText().toString().equals("") || hora.getText().toString().equals("") || cantidad.getText().toString().equals("")){
                        AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramarpienso.getContext());
                        alerta.setTitle("No se puedo programar la dispensación");
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
                        Float kg = Float.parseFloat(cantidad.getText().toString());
                        String mensaje = "Programación de dispensación de pienso: " +kg+" kg en el corral: " + posicionarray;
                        Notificacion auxiliar = new Notificacion(fecha.getText().toString(),hora.getText().toString(),mensaje,4);
                        miGranja.addNotificacion(auxiliar);
                        AlertDialog.Builder alerta = new AlertDialog.Builder(dialogoprogramarpienso.getContext());
                        alerta.setTitle("Dispensación programada con éxito");
                        alerta.setMessage("La dispensación fue programada con éxito, para consultar todas las programaciones por favor " +
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
                    dialogoprogramarpienso.dismiss();


                }
            });

            cancelar = (Button) dialogoprogramarpienso.findViewById(R.id.cancelar);
            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogoprogramarpienso.dismiss();
                }
            });



            dialogoprogramarpienso.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogoprogramarpienso.show();

    }

    public void dispensacionmanual(View v){

        Button aceptar;
        Button cancelar;

        dialogodispensacionmanual.setContentView(R.layout.dialogoanyadirpienso);
        cantidadpienso = (EditText) dialogodispensacionmanual.findViewById(R.id.cantidad);

        aceptar = (Button) dialogodispensacionmanual.findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Float kg = Float.parseFloat(cantidadpienso.getText().toString());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();
                String fechas = dateFormat.format(date);
                DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
                String hora = hourFormat.format(date);
                Dispensacion dispensacion = new Dispensacion("calidad muy buena",fechas,hora,kg);
                composicion.setText(dispensacion.getComposicion());
                cantidad.setText(Float.toString(dispensacion.getCantidad()));
                fecha.setText(dispensacion.getFecha()+ " "+ dispensacion.getHora());
                miGranja.getCorrales().get(posicionarray).addDispensacion(dispensacion);
                corral = miGranja.getCorrales().get(posicionarray);

                dialogodispensacionmanual.dismiss();

            }
        });

        cancelar = (Button) dialogodispensacionmanual.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogodispensacionmanual.dismiss();
            }
        });

        dialogodispensacionmanual.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogodispensacionmanual.show();
    }

    public void verdispensaciones(View v){

        startActivityForResult(historialpienso.getCallingIntent(this, miGranja,corral), mRequestCode);
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

    public static Intent getCallingIntent(Context context, MiGranja miGranja, Corral corral,int posicionarray){

        Intent intent = new Intent(context, pienso.class );
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
        }
    }
}
