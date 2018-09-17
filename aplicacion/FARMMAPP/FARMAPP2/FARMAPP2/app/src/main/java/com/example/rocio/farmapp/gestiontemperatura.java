package com.example.rocio.farmapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class gestiontemperatura extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private MiGranja miGranja;
    private Corral corral;
    private int mRequestCode = 100;
    private int posicionarray;
    private EditText temperatura;
    private TextView trecomendada;
    private TextView tactual;
    private TextView humedad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestiontemperatura);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");
        corral = (Corral)b.getSerializable("corral");
        posicionarray = b.getInt("posicionarray");

        temperatura = (EditText) findViewById(R.id.temperatura);
        trecomendada = (TextView) findViewById(R.id.trecomendada);
        tactual = (TextView) findViewById(R.id.tactual);
        humedad = (TextView) findViewById(R.id.humedad);

        trecomendada.setText(Float.toString(corral.getTemperatura().getTempMaxima()-corral.getTemperatura().getTempMinima())+"ºC");
        tactual.setText(Float.toString(corral.getTemperatura().getTempActual()) + "ºC");
        humedad.setText(Float.toString(corral.getTemperatura().getHumedad()) + "%");

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    public void guardarcambios(View w){
        if(temperatura.getText().toString().equals("")){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("No se puedo cambiar la temperatura");
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

        else{
            AlertDialog.Builder confirmacion = new AlertDialog.Builder(this);

            confirmacion.setTitle("Cambiar la temperatura");
            confirmacion.setMessage("¿Estás seguro que deseas cambiar la temperatura para este corral?");
            confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                    miGranja.getCorrales().get(posicionarray).getTemperatura().setTempActual(Float.parseFloat(temperatura.getText().toString()));
                    corral = miGranja.getCorrales().get(posicionarray);
                    tactual.setText(Float.toString(corral.getTemperatura().getTempActual()) + "ºC");
                    temperatura.setText("");

                    dialog.dismiss();
                }
            });

            confirmacion.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    temperatura.setText("");
                    dialog.dismiss();
                }
            });
            AlertDialog alert = confirmacion.create();
            alert.show();
        }

    }

    public void cancelar(View w){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Has cancelado el cambio de temperatura");
        alerta.setMessage("Has cancelado el cambio de temperatura");
        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                dialog.dismiss();
            }
        });

        AlertDialog alert = alerta.create();
        alert.show();
        temperatura.setText("");
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

        Intent intent = new Intent(context, gestiontemperatura.class );
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
