package com.example.rocio.farmapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.Serializable;

public class gestiondespensa extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Dialog dialogoanyadirpienso;
    private Dialog dialogoanyadirpagua;
    private MiGranja migranja;
    private TextView cantidadpienso;
    private TextView cantidadadAgua;
    private int datos;
    private int mRequestCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestiondespensa);
        dialogoanyadirpienso = new Dialog(this);
        dialogoanyadirpagua = new Dialog(this);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        datos = 0;
        migranja = (MiGranja)b.getSerializable("miGranja");
        cantidadpienso = (TextView)findViewById(R.id.cantidadpienso);
        cantidadadAgua = (TextView)findViewById(R.id.cantidadagua);

        cantidadpienso.setText(migranja.getDespensa().getCantidadComida());
        cantidadadAgua.setText(migranja.getDespensa().getCantidadAgua());

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);


    }
    public static Intent getCallingIntent(Context context, MiGranja miGranja){

        Intent intent = new Intent(context, gestiondespensa.class );
        intent.putExtra("miGranja", miGranja);
        return intent;
    }

    public void anyadirpienso(View v){
        final EditText cantidad;
        Button aceptar;
        Button cancelar;

        dialogoanyadirpienso.setContentView(R.layout.dispensacionmanual);
        cantidad = (EditText) dialogoanyadirpienso.findViewById(R.id.cantidad);


        aceptar = (Button) dialogoanyadirpienso.findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cantidad.getText().toString().equals("")){

                }
                else{
                    datos = Integer.parseInt(cantidad.getText().toString());
                    migranja.getDespensa().addComida(datos);
                    cantidadpienso.setText(migranja.getDespensa().getCantidadComida());
                    cantidadadAgua.setText(migranja.getDespensa().getCantidadAgua());
                }
                dialogoanyadirpienso.dismiss();
            }
        });

        cancelar = (Button) dialogoanyadirpienso.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoanyadirpienso.dismiss();
            }
        });

        dialogoanyadirpienso.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogoanyadirpienso.show();

    }

    public void anyadiragua(View v){
        final EditText cantidad;
        Button aceptar;
        Button cancelar;

        dialogoanyadirpagua.setContentView(R.layout.dialogoanyadirpagua);
        cantidad = (EditText) dialogoanyadirpagua.findViewById(R.id.cantidad);

        aceptar = (Button) dialogoanyadirpagua.findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cantidad.getText().toString().equals("")){

                }
                else{
                    datos = Integer.parseInt(cantidad.getText().toString());
                    migranja.getDespensa().addAgua(datos);
                    cantidadpienso.setText(migranja.getDespensa().getCantidadComida());
                    cantidadadAgua.setText(migranja.getDespensa().getCantidadAgua());

                }
                dialogoanyadirpagua.dismiss();

            }
        });

        cancelar = (Button) dialogoanyadirpagua.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoanyadirpagua.dismiss();
            }
        });

        dialogoanyadirpagua.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogoanyadirpagua.show();
    }

    public void selectItemDrawer (MenuItem menuItem){
        Intent activity = null ;
        switch (menuItem.getItemId()){
            case R.id.cuenta:
                break;
            case R.id.notificaciones:
                startActivityForResult(notificaciones.getCallingIntent(this, migranja),mRequestCode);
                break;
            case R.id.nanimales:
                startActivityForResult(animales.getCallingIntent(this, migranja),mRequestCode);
                break;
            case R.id.nveterinario:
                startActivity(gestionveterinario.getCallingIntent(this, migranja));
                break;
            case R.id.ncompras:
                startActivityForResult(gestioncompras.getCallingIntent(this, migranja), mRequestCode);
                break;
            case R.id.ninstalaciones:
                startActivityForResult(visualizarcorral.getCallingIntent(this, migranja), mRequestCode);
                break;
            case R.id.ndespensa:
                startActivityForResult(gestiondespensa.getCallingIntent(this, migranja), mRequestCode);
                break;
            case R.id.ngastos:
                startActivityForResult(controlgastos.getCallingIntent(this, migranja),mRequestCode);
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
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("miGranja", migranja);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && resultCode == RESULT_OK) {
            migranja = (MiGranja)data.getSerializableExtra("miGranja");
        }
    }
}
