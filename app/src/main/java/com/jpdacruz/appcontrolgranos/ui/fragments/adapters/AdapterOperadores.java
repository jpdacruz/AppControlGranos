package com.jpdacruz.appcontrolgranos.ui.fragments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpdacruz.appcontrolgranos.R;
import com.jpdacruz.appcontrolgranos.db.clases.Operador;

import java.util.ArrayList;

public class AdapterOperadores extends RecyclerView.Adapter<AdapterOperadores.ViewHolder>
        implements View.OnClickListener{

    private ArrayList<Operador> operadores;
    private View.OnClickListener listener;

    public AdapterOperadores(ArrayList<Operador> operadores) {

        this.operadores = operadores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemmolinos,parent,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvNumeroOperador.setText("Numero Operador: " + operadores.get(position).getNumeroOperador());
        holder.tvCuit.setText("CUIT: " + operadores.get(position).getCuit());
        holder.tvRazonSocial.setText("Razon social: " + operadores.get(position).getRazonSocial());
    }

    public void setOnClickListener (View.OnClickListener listener) {

        this.listener = listener;
    }

    public void onClick(View v) {

        if (listener != null){

            listener.onClick(v);
        }
    }

    @Override
    public int getItemCount() {
        return operadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNumeroOperador, tvCuit, tvRazonSocial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNumeroOperador = itemView.findViewById(R.id.cardViewOperador);
            tvCuit = itemView.findViewById(R.id.cardViewOperadorCUIT);
            tvRazonSocial = itemView.findViewById(R.id.cardViewOperadorRazonSocial);
        }
    }
}
