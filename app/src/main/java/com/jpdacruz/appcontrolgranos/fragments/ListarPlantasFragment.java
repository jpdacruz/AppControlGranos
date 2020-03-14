package com.jpdacruz.appcontrolgranos.fragments;

import android.graphics.Path;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jpdacruz.appcontrolgranos.R;
import com.jpdacruz.appcontrolgranos.adapters.AdapterOperadores;
import com.jpdacruz.appcontrolgranos.adapters.AdapterPlantas;
import com.jpdacruz.appcontrolgranos.clases.Constantes;
import com.jpdacruz.appcontrolgranos.clases.Operador;
import com.jpdacruz.appcontrolgranos.clases.Planta;

import java.util.ArrayList;

public class ListarPlantasFragment extends Fragment {

    //widgets
    private TextView mNumeroOperador, mCuit, mRazonSocial;
    private RecyclerView recyclerPlantas;
    private AdapterPlantas adapterPlantas;

    //vars
    private Operador operadorEnviado;
    private FirebaseDatabase database;
    private DatabaseReference refOperador;
    private ArrayList<Operador> operadores;
    private ArrayList<Planta> plantas;

    public ListarPlantasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listar_plantas, container, false);

        iniciarDataBase();
        iniciarComponentes(view);
        comprobarBundle();
        iniciarRecyclerViewPlanta();
        obtenerDatosFirebase();

        return view;
    }

    private void iniciarDataBase() {

        FirebaseApp.initializeApp(getContext());
        database = FirebaseDatabase.getInstance();
        refOperador= database.getReference(Constantes.PATH_OPERADOR);
    }

    private void obtenerDatosFirebase() {

        operadores = new ArrayList<>();
        refOperador.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                operadores.clear();
                plantas.clear();
                for(DataSnapshot objetoDatasnapshot : dataSnapshot.getChildren()){

                    Operador operador = objetoDatasnapshot.getValue(Operador.class);
                    operadores.add(operador);
                }

                listarPlantasOperadorSeleccionado();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            private void listarPlantasOperadorSeleccionado() {

                for (Operador operador : operadores){

                    if(operador.getId().toLowerCase().contains(operadorEnviado.getId().toLowerCase())){

                        plantas = operador.getPlantas();
                        adapterPlantas = new AdapterPlantas(plantas);
                        recyclerPlantas.setAdapter(adapterPlantas);
                    }
                }
            }
        });
    }
    private void iniciarRecyclerViewPlanta() {

        recyclerPlantas.setLayoutManager(new LinearLayoutManager(getContext()));
        plantas = new ArrayList<>();
    }

    private void comprobarBundle() {
        if (getArguments() != null) {

            operadorEnviado = (Operador) getArguments().getSerializable("operador");
            asignarDatosOperador(operadorEnviado);
        }
    }

    private void iniciarComponentes(View view) {

        recyclerPlantas = view.findViewById(R.id.recyclerPlantas);
        mNumeroOperador = view.findViewById(R.id.listarPlantasOperador);
        mCuit = view.findViewById(R.id.listarPlantasCUIT);
        mRazonSocial = view.findViewById(R.id.listarPlantasRazonSocial);
    }

    private void asignarDatosOperador(Operador operadorEnviado) {

        mNumeroOperador.setText("Operador: " + operadorEnviado.getNumeroOperador());
        mCuit.setText("CUIT: " + operadorEnviado.getCuit());
        mRazonSocial.setText("Razon social: " + operadorEnviado.getRazonSocial());
    }
}
