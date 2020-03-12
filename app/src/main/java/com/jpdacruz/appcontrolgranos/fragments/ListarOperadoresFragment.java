package com.jpdacruz.appcontrolgranos.fragments;

import android.content.Context;
import android.os.Bundle;

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

import java.util.ArrayList;

public class ListarOperadoresFragment extends Fragment {

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
    private TomarDatosFragmentListener listener;

    public ListarOperadoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_operadores, container, false);

        iniciarDataBase();
        iniciarComponentes(view);
        iniciarRecyclerViewOperadores();
        obtenerDatosFirebase();
        iniciarSearchView();
        iniciarListenerRecyclerViewOperadores();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (TomarDatosFragmentListener) context;
    }

    private void iniciarListenerRecyclerViewOperadores() {

        adapterOperadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Operador operadorAmostrar = operadores.get(recyclerOperadores.getChildAdapterPosition(view));

                listener.enviarDatosOperador(operadorAmostrar);
            }
        });
    }

    private void iniciarDataBase() {

        FirebaseApp.initializeApp(getContext());
        database = FirebaseDatabase.getInstance();
        refOperador= database.getReference(Constantes.PATH_OPERADOR);
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

        refOperador.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                operadores.clear();
                for(DataSnapshot objetoDatasnapshot : dataSnapshot.getChildren()){

                    Operador operador = objetoDatasnapshot.getValue(Operador.class);

                    modificarOperador(operador, objetoDatasnapshot);

                    operadores.add(operador);
                    recyclerOperadores.getAdapter().notifyDataSetChanged();
                    refOperador.child(operador.getId()).setValue(operador);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            private void modificarOperador(Operador operador, DataSnapshot objetoDatasnapshot) {

                operador.setId(objetoDatasnapshot.getKey());
                operador.setSeoOperador(operador.getNumeroOperador() + " "
                        + operador.getCuit() + " "
                        + operador.getRazonSocial());
            }
        });
    }

    private void iniciarRecyclerViewOperadores() {

        recyclerOperadores.setLayoutManager(new LinearLayoutManager(getContext()));
        operadores = new ArrayList<>();
        adapterOperadores = new AdapterOperadores(operadores);
        recyclerOperadores.setAdapter(adapterOperadores);
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

    private void iniciarComponentes(View view) {

        recyclerOperadores = view.findViewById(R.id.recyclerViewOperadores);
        searchViewOperadores = view.findViewById(R.id.searchViewOperadores);
    }

    public interface TomarDatosFragmentListener {

        void enviarDatosOperador(Operador operadorAmostrar);
    }


}
