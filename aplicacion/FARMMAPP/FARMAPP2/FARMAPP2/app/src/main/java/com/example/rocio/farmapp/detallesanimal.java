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
import android.widget.CheckBox;
import android.widget.EditText;

public class detallesanimal extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private MiGranja miGranja;
    private Animal animal;
    private EditText fecha;
    private EditText codigo;
    private EditText raza ;
   private EditText observaciones;
    private CheckBox hembra;
    private CheckBox macho;
    private int tipoanimal;
    private int mRequestCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallesanimal);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");
        animal = (Animal) b.getSerializable("animal");
        tipoanimal = b.getInt("tipoanimal");

        //Cargar datos
        fecha = (EditText) findViewById(R.id.fecha);
         codigo = (EditText) findViewById(R.id.codigo);
         raza = (EditText) findViewById(R.id.raza);
         observaciones = (EditText) findViewById(R.id.observaciones);
         hembra = (CheckBox) findViewById(R.id.hembra);
         macho = (CheckBox) findViewById(R.id.macho);

        fecha.setText(animal.getFechaNacimiento());
        codigo.setText(animal.getCodExplotacion());
        raza.setText(animal.getRaza());
        observaciones.setText(animal.getObservaciones());
        if(animal.getSexo().equals("hembra")){
            hembra.setChecked(true);
        }else macho.setChecked(true);



        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    public void eliminaranimal (View v){

        AlertDialog.Builder confirmacion = new AlertDialog.Builder(this);

        confirmacion.setTitle("Eliminar animal");
        confirmacion.setMessage("¿Estás seguro que deseas eliminar este animal?");
        confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                miGranja.deleteAnimal(tipoanimal,animal.getCodExplotacion());

                fecha.setText("");
                codigo.setText("");
                raza.setText("");
                observaciones.setText("");
                if(animal.getSexo().equals("hembra")){
                    hembra.setChecked(true);
                }else macho.setChecked(true);

                dialog.dismiss();
            }
        });

        confirmacion.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog alert =confirmacion.create();
        alert.show();
    }

    public void editardatos (View v){
        startActivityForResult(editaranimal.getCallingIntent(this, miGranja,animal,tipoanimal),mRequestCode);
    }

    public void fichaveterinario(View v){
    Intent intent = new Intent(getApplicationContext(),fichaveterinario.class);
    startActivity(intent);
    }

    public static Intent getCallingIntent(Context context, MiGranja miGranja, Animal animal, int tipoanimal){

        Intent intent = new Intent(context, detallesanimal.class );
        intent.putExtra("miGranja", miGranja);
        intent.putExtra("animal", animal) ;
        intent.putExtra("tipoanimal",tipoanimal);
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
