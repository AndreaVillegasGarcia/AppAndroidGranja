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
import android.view.View;

public class selecionanimalvergestacion extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private MiGranja miGranja;
    private  int mRequestCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionanimalvergestacion);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }


    public void abrirvaca (View w){
        startActivity(visualizaranimalesengestacion.getCallingIntent(this, miGranja,0,0));
    }

    public void abrircerdo (View w){
        startActivity(visualizaranimalesengestacion.getCallingIntent(this, miGranja,1,0));
    }

    public void abriroveja (View w){
        startActivity(visualizaranimalesengestacion.getCallingIntent(this, miGranja,3,0));
    }

    public void abrirgallina (View w) {
        startActivity(visualizaranimalesengestacion.getCallingIntent(this, miGranja,2,0));
    }

    public void abrircabra (View w){
        startActivity(visualizaranimalesengestacion.getCallingIntent(this, miGranja,4,0));
    }

    public void abrirotros (View v){
        startActivity(visualizaranimalesengestacion.getCallingIntent(this, miGranja,5,0));
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
                break;
            case R.id.ncompras:
                break;
            case R.id.ninstalaciones:
                break;
            case R.id.ndespensa:
                break;
            case R.id.ngastos:
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

        Intent intent = new Intent(context, selecionanimalvergestacion.class );
        intent.putExtra("miGranja", miGranja);
        return intent;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("miGranja", miGranja);
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
