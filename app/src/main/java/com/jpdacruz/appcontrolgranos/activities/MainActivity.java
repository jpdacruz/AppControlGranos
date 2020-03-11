package com.jpdacruz.appcontrolgranos.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jpdacruz.appcontrolgranos.R;
import com.jpdacruz.appcontrolgranos.activities.CargarMolinoActivity;
import com.jpdacruz.appcontrolgranos.adapters.AdapterOperadores;
import com.jpdacruz.appcontrolgranos.clases.Constantes;
import com.jpdacruz.appcontrolgranos.clases.Operador;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //widgets
    private RecyclerView recyclerOperadores;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private AdapterOperadores adapterOperadores;
    private AdapterOperadores adapterOperadoresEncontrados;
    private SearchView searchViewOperadores;

    //vars
    private FirebaseDatabase database;
    private DatabaseReference refOperador;
    private ArrayList<Operador> operadores;
    private ArrayList<Operador> busquedaOperadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();
        iniciarBotones();
        iniciarRecyclerViewOperadores();
        obtenerDatosFirebase();
        iniciarSearchView();
    }

    private void iniciarSearchView() {

        searchViewOperadores.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                buscarSeoOperadores(newText);
                return true;
            }
        });
    }

    private void obtenerDatosFirebase() {

        database = FirebaseDatabase.getInstance();
        refOperador= database.getReference(Constantes.PATH_OPERADOR);

        refOperador.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Operador operador = dataSnapshot.getValue(Operador.class);
                operadores.add(operador);
                recyclerOperadores.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Operador operador = dataSnapshot.getValue(Operador.class);
                operadores.set(operadores.indexOf(operador), operador);
                recyclerOperadores.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                Operador operador = dataSnapshot.getValue(Operador.class);
                operadores.remove(operador);
                recyclerOperadores.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void iniciarRecyclerViewOperadores() {

        recyclerOperadores.setLayoutManager(new LinearLayoutManager(this));
        operadores = new ArrayList<>();
        adapterOperadores = new AdapterOperadores(operadores);
        recyclerOperadores.setAdapter(adapterOperadores);
    }

    private void iniciarBotones() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), CargarMolinoActivity.class));
            }
        });
    }

    private void buscarSeoOperadores(String newText) {

        busquedaOperadores = new ArrayList<>();

        for (Operador operadorEncontrado : operadores){

            if(operadorEncontrado.getSeoOperador().toLowerCase().contains(newText.toLowerCase())){

                busquedaOperadores.add(operadorEncontrado);
            }

            adapterOperadoresEncontrados = new AdapterOperadores(busquedaOperadores);
            recyclerOperadores.setAdapter(adapterOperadoresEncontrados);
        }
    }

    private void iniciarComponentes() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        recyclerOperadores = findViewById(R.id.recyclerViewOperadores);
        searchViewOperadores = findViewById(R.id.searchViewOperadores);

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
}
