package com.jpdacruz.appcontrolgranos.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jpdacruz.appcontrolgranos.R;
import com.jpdacruz.appcontrolgranos.clases.Operador;

public class ListarPlantasFragment extends Fragment {

    private TextView mNumeroOperador, mCuit, mRazonSocial;

    private Operador operadorEnviado;

    public ListarPlantasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listar_plantas, container, false);

        iniciarComponentes(view);
        comprobarBundle();

        return view;
    }

    private void comprobarBundle() {
        if (getArguments() != null) {

            operadorEnviado = (Operador) getArguments().getSerializable("operador");
            asignarDatosOperador(operadorEnviado);
        }
    }

    private void iniciarComponentes(View view) {

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
