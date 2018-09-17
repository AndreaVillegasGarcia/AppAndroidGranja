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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class controlgastos extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Dialog anyadirgastos;
    private MiGranja miGranja;
    private TextView pienso;
    private TextView agua;
    private TextView utiles;
    private TextView veterinario;
    private TextView total;
    int posicion;
    private int mRequestCode = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlgastos);
        anyadirgastos = new Dialog(this);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");
        pienso = (TextView)findViewById(R.id.pienso);
        agua = (TextView)findViewById(R.id.agua);
        utiles = (TextView)findViewById(R.id.utiles);
        veterinario = (TextView)findViewById(R.id.veterinario);
        total = (TextView)findViewById(R.id.total);

        pienso.setText(""+miGranja.getGastos().getgPienso());
        agua.setText(""+miGranja.getGastos().getgAgua());
        utiles.setText(""+miGranja.getGastos().getgUtiles());
        veterinario.setText(""+miGranja.getGastos().getgVeterinario());
        total.setText(""+miGranja.getGastos().getTotal());

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    public void anyadirgasto(View v){
        Spinner tipogasto ;
        final String[] items;
        final boolean itsfirstime = true;
        Button aceptar;
        Button cancelar;
        final EditText gastos;

        anyadirgastos.setContentView(R.layout.dialogogastos);
        tipogasto = (Spinner) anyadirgastos.findViewById(R.id.spinner);
        items = getResources().getStringArray(R.array.tipogastos);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipogasto.setAdapter(adapter);
        tipogasto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_LONG).show();
                posicion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gastos = (EditText)anyadirgastos.findViewById(R.id.precio);
        aceptar = (Button) anyadirgastos.findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(gastos.getText().toString().equals("")){

                }
                else{
                    miGranja.getGastos().addGasto(posicion, Float.parseFloat(gastos.getText().toString()));

                    pienso.setText(""+miGranja.getGastos().getgPienso());
                    agua.setText(""+miGranja.getGastos().getgAgua());
                    utiles.setText(""+miGranja.getGastos().getgUtiles());
                    veterinario.setText(""+miGranja.getGastos().getgVeterinario());
                    total.setText(""+miGranja.getGastos().getTotal());
                }
                anyadirgastos.dismiss();
            }
        });

        cancelar = (Button) anyadirgastos.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anyadirgastos.dismiss();
            }
        });


        anyadirgastos.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        anyadirgastos.show();


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

        Intent intent = new Intent(context, controlgastos.class );
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
