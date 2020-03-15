package com.jpdacruz.appcontrolgranos.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jpdacruz.appcontrolgranos.R;
import com.jpdacruz.appcontrolgranos.clases.Operador;
import com.jpdacruz.appcontrolgranos.fragments.ListarOperadoresFragment;
import com.jpdacruz.appcontrolgranos.fragments.ListarPlantasFragment;
import com.jpdacruz.appcontrolgranos.interfaces.CallBackInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements CallBackInterface {

    //widgets
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();
        iniciarBotones();
        irFragmentOperadores();
    }

    private void irFragmentOperadores() {

        ListarOperadoresFragment listarOperadoresFragment = new ListarOperadoresFragment();
        listarOperadoresFragment.setCallBackInterface(this);

        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment,listarOperadoresFragment).commit();
    }

    private void irFragmentPlantas(Operador operador) {

        ListarPlantasFragment listarPlantasFragment = new ListarPlantasFragment();
        listarPlantasFragment.setCallBackInterface(this);

        Bundle datosOperador = new Bundle();
        datosOperador.putSerializable("operador" ,operador);
        listarPlantasFragment.setArguments(datosOperador);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment,listarPlantasFragment).addToBackStack(null).commit();
    }

    private void irActivityCargarOperadoresAgregarPlanta(Operador operador) {

        Intent intent = new Intent(this, CargarMolinoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("operador", operador);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void iniciarBotones() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), CargarMolinoActivity.class));
            }
        });
    }

    private void iniciarComponentes() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callBackMainActivity(Operador operador) {

        irFragmentPlantas(operador);
    }

    @Override
    public void callBackToAddPlanta(Operador operador) {

        irActivityCargarOperadoresAgregarPlanta(operador);
    }


}
