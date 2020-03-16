package com.jpdacruz.appcontrolgranos.fragments;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jpdacruz.appcontrolgranos.R;
import com.jpdacruz.appcontrolgranos.adapters.AdapterOperadores;
import com.jpdacruz.appcontrolgranos.clases.Constantes;
import com.jpdacruz.appcontrolgranos.clases.Operador;
import com.jpdacruz.appcontrolgranos.interfaces.CallBackInterface;

import java.util.ArrayList;

public class ListarOperadoresFragment extends Fragment {

    private static final String TAG = "ListarOperadoresFragmen";

    //widgets
    private RecyclerView recyclerOperadores;
    private AdapterOperadores adapterOperadores;
    private AdapterOperadores adapterOperadoresEncontrados;
    private SearchView searchViewOperadores;

    //vars
    private FirebaseDatabase database;
    private DatabaseReference refOperador;
    private ArrayList<Operador> operadores = new ArrayList<>();
    private ArrayList<Operador> busquedaOperadores = new ArrayList<>();
    private CallBackInterface callBackInterface;

    public ListarOperadoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_operadores, container, false);

        Log.d(TAG,"obteniendo vista recycler");
        recyclerOperadores = view.findViewById(R.id.recyclerViewOperadores);
        searchViewOperadores = view.findViewById(R.id.searchViewOperadores);

        iniciarDataBase();

        obtenerDatosFirebase();
        iniciarSearchView();

        return view;
    }

    private void iniciarDataBase() {

        Log.d(TAG,"iniciardo database");

        FirebaseApp.initializeApp(getContext());
        database = FirebaseDatabase.getInstance();
        refOperador= database.getReference(Constantes.PATH_OPERADOR);
    }

    private void obtenerDatosFirebase() {

        Log.d(TAG,"obteniendo datos");

        refOperador.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                operadores.clear();
                for(DataSnapshot objetoDatasnapshot : dataSnapshot.getChildren()){

                    Operador operador = objetoDatasnapshot.getValue(Operador.class);

                    modificarOperador(operador, objetoDatasnapshot);

                    Log.d(TAG,"agregando operadores al recycler");

                    operadores.add(operador);

                    iniciarRecyclerViewOperadores();

                    Log.d(TAG,"nuevos values operador");
                    refOperador.child(operador.getId()).setValue(operador);
                    recyclerOperadores.getAdapter().notifyDataSetChanged();
                    iniciarListenerRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            private void modificarOperador(Operador operador, DataSnapshot objetoDatasnapshot) {

                Log.d(TAG,"modificando operador");
                operador.setId(objetoDatasnapshot.getKey());
                operador.setSeoOperador(operador.getNumeroOperador() + " "
                        + operador.getCuit() + " "
                        + operador.getRazonSocial());
            }
        });
    }

    private void iniciarRecyclerViewOperadores() {

        Log.d(TAG,"seteando recycler");
        recyclerOperadores.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterOperadores = new AdapterOperadores(operadores);
        recyclerOperadores.setAdapter(adapterOperadores);
        Log.d(TAG,"notificando cambios recycler");

    }

    private void iniciarListenerRecyclerView() {

        adapterOperadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG,"iniciando callback a main to plantas");
                Operador operadorAmostrar = operadores.get(recyclerOperadores.getChildAdapterPosition(view));

                if (callBackInterface != null) {
                    callBackInterface.callBackMainActivity(operadorAmostrar);
                }
            }
        });
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

    private void buscarSeoOperadores(String newText) {

        Log.d(TAG,"buscando operador");
         for (Operador operadorEncontrado : operadores){

            if(operadorEncontrado.getSeoOperador().toLowerCase().contains(newText.toLowerCase())){

                busquedaOperadores.add(operadorEncontrado);
            }
             Log.d(TAG,"seteando recycler operadores encontrados");
            adapterOperadoresEncontrados = new AdapterOperadores(busquedaOperadores);
            recyclerOperadores.setAdapter(adapterOperadoresEncontrados);
        }
    }

    public void setCallBackInterface(CallBackInterface callBackInterface){

        this.callBackInterface = callBackInterface;
    }
}
