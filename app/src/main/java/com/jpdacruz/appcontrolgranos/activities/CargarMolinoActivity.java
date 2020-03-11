package com.jpdacruz.appcontrolgranos.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jpdacruz.appcontrolgranos.R;
import com.jpdacruz.appcontrolgranos.clases.Constantes;
import com.jpdacruz.appcontrolgranos.clases.InterfaceGeneral;
import com.jpdacruz.appcontrolgranos.clases.Molino;
import com.jpdacruz.appcontrolgranos.clases.Operador;
import com.jpdacruz.appcontrolgranos.clases.Planta;

import java.util.ArrayList;


public class CargarMolinoActivity extends AppCompatActivity implements InterfaceGeneral {

    //vars
    private LocationManager ubicacion;
    private LocationListener locationListener;
    private int permisionCheck;
    private FirebaseDatabase database;
    private DatabaseReference referenceMolino;
    private DatabaseReference referenceOperador;
    private ArrayList<Planta> plantas;
    private Operador operador;
    private Molino molino;

    //widgets
    Toolbar toolbar;
    EditText mNumeroOperador, mCuit, mRazonSocial,mNumeroPlanta, mLocalidad, mGPSsur, mGPSoeste, mDireccion,mEmail;
    Button btnGps, btnAgregarMolino;
    Spinner spinnerCategoria, spinnerProvincia,spinnerProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_molino);

        iniciarComponentes();
        iniciarGPS();
        iniciarBotones();
    }

    private void iniciarComponentes() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNumeroOperador = findViewById(R.id.MolinoeditTextOperador);
        mCuit = findViewById(R.id.MOLINOeditTextCUIT);
        mRazonSocial = findViewById(R.id.MolinoeditTextRazonSocial);
        mNumeroPlanta = findViewById(R.id.MOLINOeditTextPLANTA);
        mLocalidad = findViewById(R.id.MOLINOeditTextLocalidad);
        mGPSsur = findViewById(R.id.MOLINOeditTextGPSsur);
        mGPSoeste = findViewById(R.id.MOLINOeditTextGPOeste);
        mDireccion = findViewById(R.id.MOLINOeditTextDireccion);
        mEmail = findViewById(R.id.MOLINOeditTextEmail);

        btnAgregarMolino = findViewById(R.id.btnAgregarMolino);
        btnGps = findViewById(R.id.btngps);

        spinnerCategoria = findViewById(R.id.molinoSpinnerActividad);
        spinnerProvincia = findViewById(R.id.molinoSpinnerProvincia);
        spinnerProveedor = findViewById(R.id.molinoSpinnerProveedor);
    }

    private void iniciarGPS() {

        permisionCheck = ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permisionCheck == PackageManager.PERMISSION_DENIED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }
    }

    private void iniciarBotones() {

        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ejecutarGPS();
            }
        });

        btnAgregarMolino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!verificacionesEdixText(view)){

                    return;

                }else {

                    insertarDatosDB();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });
    }

    private void insertarDatosDB() {

        instanciarBaseDatos();
        crearObjetoMolino();
    }

    private void instanciarBaseDatos() {

        database = FirebaseDatabase.getInstance();
        referenceMolino = database.getReference(Constantes.PATH_MOLINO);
        referenceOperador = database.getReference(Constantes.PATH_OPERADOR);
    }

    private void crearObjetoMolino() {

        operador = new Operador();
        plantas = new ArrayList<>();

        operador.setNumeroOperador(mNumeroOperador.getText().toString());
        operador.setCuit(mCuit.getText().toString());
        operador.setRazonSocial(mRazonSocial.getText().toString());

        operador.setSeoOperador(operador.getNumeroOperador() + " "
                                + operador.getCuit() + " "
                                + operador.getRazonSocial());

        molino = new Molino();

        molino.setNumeroOperador(mNumeroOperador.getText().toString());
        molino.setCuit(mCuit.getText().toString());
        molino.setRazonSocial(mRazonSocial.getText().toString());
        molino.setNumeroPlanta(mNumeroPlanta.getText().toString());
        molino.setProvincia(spinnerProvincia.getSelectedItem().toString());
        molino.setLocalidad(mLocalidad.getText().toString());
        molino.setGpsSur(mGPSsur.getText().toString());
        molino.setGpsOeste(mGPSoeste.getText().toString());
        molino.setDireccion(mDireccion.getText().toString());
        molino.setEmail(mEmail.getText().toString());
        molino.setCantInspecciones("0");
        molino.setCategoria(spinnerCategoria.getSelectedItem().toString());
        molino.setProveedor(spinnerProveedor.getSelectedItem().toString());

        plantas.add(molino);
        operador.setPlantas(plantas);

        referenceOperador.push().setValue(operador);
        referenceMolino.push().setValue(molino);
    }

    private void ejecutarGPS() {

        ubicacion = (LocationManager)
                CargarMolinoActivity.this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {

                mGPSsur.setText(formatearDecimales(location.getLatitude(),5).toString());
                mGPSoeste.setText(formatearDecimales(location.getLongitude(),5).toString());
                ubicacion.removeUpdates(locationListener);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        permisionCheck = ContextCompat.checkSelfPermission
                (CargarMolinoActivity.this,Manifest.permission.ACCESS_FINE_LOCATION);

        ubicacion.requestLocationUpdates
                (LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }

    @Override
    public Double formatearDecimales(Double numero, Integer numeroDecimales) {

        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    @Override
    public Boolean verificarEditText(EditText editText, String string, View view) {

        if (editText.getText().toString().isEmpty()){

            informarError(string, view);
            return false;

        } else {

            return true;
        }
    }

    @Override
    public Boolean verificarSpinner(Spinner spinner, String string, View view) {

        if(spinner.getSelectedItemPosition()==0){

            informarError(string, view);

            return false;

        }else {

            return true;}
    }

    private boolean verificacionesEdixText(View view) {

        if (!verificarEditText(mNumeroOperador, "Numero Operador", view)){

            return false;

        }else if (!verificarEditText(mCuit, "CUIT", view)){

            return false;

        }else if (!verificarEditText(mRazonSocial,"Razon Social", view)){

            return false;

        }else if (!verificarEditText(mNumeroPlanta,"Numero Planta", view)){

            return false;

        }else if (!verificarSpinner(spinnerProvincia, "Selecciona Provincia", view)){

            return false;

        }else if (!verificarEditText(mLocalidad, "Localidad", view)){

            return false;

        }else if (!verificarEditText(mGPSsur, "ubicacion GPS", view)){

            return false;

        }else if (!verificarEditText(mGPSoeste,"ubicacion GPS", view)){

            return false;

        }else if (!verificarEditText(mDireccion, "Direccion", view)){

            return false;

        }else if (!verificarEditText(mEmail, "Email", view)){

            return false;

        }else if (!verificarSpinner(spinnerCategoria, "Selecciona Categoria", view)){

            return false;

        }else if (!verificarSpinner(spinnerProveedor, "Selecciona Proveedor CEMT", view)){

            return false;

        }else {

            return true;
        }
    }

    @Override
    public void informarError(String string, View view) {

        Snackbar.make(view,"Debe agregar: " + string,Snackbar.LENGTH_LONG).show();
    }
}
