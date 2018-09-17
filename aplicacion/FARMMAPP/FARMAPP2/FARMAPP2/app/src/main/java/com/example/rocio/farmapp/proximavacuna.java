package com.example.rocio.farmapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;

public class proximavacuna extends AppCompatActivity {

    private ListView lista;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private MiGranja miGranja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximavacuna);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");

        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new Adaptadorproximasvacunas(this));

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    public void selectItemDrawer (MenuItem menuItem){
        Intent activity = null ;
        switch (menuItem.getItemId()){
            case R.id.cuenta:
                break;
            case R.id.notificaciones:
                break;
            case R.id.nanimales:
                activity = new Intent(getApplicationContext(),animales.class);
                break;
            case R.id.nveterinario:
                activity = new Intent(getApplicationContext(), gestionveterinario.class);
                break;
            case R.id.ncompras:
                activity = new Intent(getApplicationContext(), gestioncompras.class);
                break;
            case R.id.ninstalaciones:
                activity = new Intent(getApplicationContext(),visualizarcorral.class);
                break;
            case R.id.ndespensa:
                activity = new Intent(getApplicationContext(),gestiondespensa.class);
                break;
            case R.id.ngastos:
                activity = new Intent(getApplicationContext(),controlgastos.class);
                break;
        }
        menuItem.setChecked(true);
        startActivity(activity);
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


    public static Intent getCallingIntent(Context context, MiGranja miGranja){

        Intent intent = new Intent(context, proximavacuna.class );
        intent.putExtra("miGranja", miGranja);
        return intent;
    }
}
