package com.example.rocio.farmapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class ultimasvacunas extends AppCompatActivity {

    private ListView lista;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private MiGranja miGranja;
    private int mRequestCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimasvacunas);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");


        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new AdaptadorUltimasvacunas(this, this.miGranja));

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

    public static Intent getCallingIntent(Context context, MiGranja miGranja){

        Intent intent = new Intent(context, ultimasvacunas.class );
        intent.putExtra("miGranja", miGranja);
        return intent;
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
}
