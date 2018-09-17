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

public class anyadiranimal extends AppCompatActivity {

    private ImageView imageView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private MiGranja miGranja;
    private int tipoanimal;
    private EditText fechanacimiento;
    private EditText codigoexplotacion;
    private EditText raza;
    private CheckBox macho;
    private CheckBox hembra;
    private EditText observaciones;
    private int mRequestCode = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyadiranimal);
        imageView = (ImageView) findViewById(R.id.foto);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        miGranja = (MiGranja)b.getSerializable("miGranja");
        tipoanimal = (Integer)b.getInt("tipoanimal");

        fechanacimiento = (EditText) findViewById(R.id.fecha);
        codigoexplotacion = (EditText) findViewById(R.id.codigo);
        raza = (EditText)findViewById(R.id.raza);
        macho = (CheckBox) findViewById(R.id.macho);
        hembra = (CheckBox) findViewById(R.id.hembra);
        observaciones = (EditText) findViewById(R.id.observaciones);

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

        if(fechanacimiento.getText().equals("")|| codigoexplotacion.getText().equals("")|| raza.getText().equals("")
                || (!macho.isChecked() && !hembra.isChecked()) || observaciones.getText().equals("")){

            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Fallo al añadir un animal");
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

            confirmacion.setTitle("Añadir nuevo animal");
            confirmacion.setMessage("¿Estás seguro que deseas añadir un nuevo animal?");
            confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                    String sexo = "";
                    if(macho.isChecked()){
                        sexo = "macho";
                    }
                    else if( hembra.isChecked()){
                        sexo = "hembra";
                    }
                    Animal aux = new Animal(fechanacimiento.getText().toString(),codigoexplotacion.getText().toString(),raza.getText().toString(),sexo,observaciones.getText().toString(),tipoanimal);
                    if(tipoanimal == 0){
                        miGranja.addVaca(aux);
                    }
                    else if(tipoanimal == 1){
                        miGranja.addCerdos(aux);
                    }
                    else if(tipoanimal == 2){
                        miGranja.addGallina(aux);
                    }
                    else if(tipoanimal == 3){
                        miGranja.addOveja(aux);
                    }
                    else if(tipoanimal ==4){
                        miGranja.addCabras(aux);
                    }
                    else if(tipoanimal == 5){
                        miGranja.addOtros(aux);
                    }

                    fechanacimiento.setText("");
                    codigoexplotacion.setText("");
                    raza.setText("");
                    hembra.setChecked(false);
                    macho.setChecked(false);
                    observaciones.setText("");



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
                fechanacimiento.setText(date);
                    }
        });
        newFragment.show(getFragmentManager(), "datePicker");

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

    public static Intent getCallingIntent(Context context, MiGranja miGranja, int tipoanimal){

        Intent intent = new Intent(context, anyadiranimal.class );
        intent.putExtra("miGranja", miGranja);
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
