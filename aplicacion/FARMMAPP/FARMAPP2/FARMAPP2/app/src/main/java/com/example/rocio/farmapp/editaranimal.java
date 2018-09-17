package com.example.rocio.farmapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

public class editaranimal extends AppCompatActivity {
    private ImageView imageView;
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
        setContentView(R.layout.activity_editaranimal);
        imageView = (ImageView) findViewById(R.id.foto);

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

    public void anyadirfoto(View v){
        cargarimange();
    }

    private void cargarimange() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri path = data.getData();
            imageView.setImageURI(path);
        }
    }

    public void anyadiranimal (View v){

        if(fecha.getText().equals("")|| codigo.getText().equals("")|| raza.getText().equals("")
                || (!macho.isChecked() && !hembra.isChecked()) || observaciones.getText().equals("")){

            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Fallo al editar un animal");
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
        else {

            AlertDialog.Builder confirmacion = new AlertDialog.Builder(this);

            confirmacion.setTitle("Editar animal");
            confirmacion.setMessage("¿Estás seguro que deseas editar el animal?");
            confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    String sexo = "";
                    if (hembra.isChecked()) sexo = "hembra";
                    else if(macho.isChecked()) sexo = "macho";

                    Animal aux = new Animal(fecha.getText().toString(),codigo.getText().toString(),raza.getText().toString(),sexo, observaciones.getText().toString(),tipoanimal);
                    miGranja.addAnimal(tipoanimal,aux);
                    miGranja.deleteAnimal(tipoanimal,animal.getCodExplotacion());

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
            AlertDialog alert = confirmacion.create();
            alert.show();
        }
    }

    public void dialogocalendario(View w){

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

    public void cancelar(View w){
        this.onBackPressed();
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

    public static Intent getCallingIntent(Context context, MiGranja miGranja, Animal animal, int tipoanimal){

        Intent intent = new Intent(context, editaranimal.class );
        intent.putExtra("miGranja", miGranja);
        intent.putExtra("animal", animal) ;
        intent.putExtra("tipoanimal", tipoanimal);
        return intent;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("miGranja", miGranja);
        setResult(RESULT_OK, intent);
        finish();
    }
}
