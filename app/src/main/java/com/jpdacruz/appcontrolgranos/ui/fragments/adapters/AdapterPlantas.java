package com.jpdacruz.appcontrolgranos.ui.fragments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpdacruz.appcontrolgranos.R;
import com.jpdacruz.appcontrolgranos.db.clases.Planta;

import java.util.ArrayList;

public class AdapterPlantas extends RecyclerView.Adapter<AdapterPlantas.ViewHolder>
                            implements View.OnClickListener{

    private ArrayList<Planta> plantas;
    private View.OnClickListener listener;

    public AdapterPlantas(ArrayList<Planta> plantas) {
        this.plantas = plantas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemplantas,parent,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mNumeroPLanta.setText(String.format("Numero de plata: %s", plantas.get(position).getNumeroPlanta()));
        holder.mProvincia.setText(String.format("Provincia: %s", plantas.get(position).getProvincia()));
        holder.mLocalidad.setText(String.format("Localidad: %s", plantas.get(position).getLocalidad()));
        holder.mNumeroVisitas.setText(String.format("Cantidad de visitas: %s", plantas.get(position).getCantInspecciones()));
    }

    @Override
    public int getItemCount() {
        return plantas.size();
    }

    public void setOnClickListener (View.OnClickListener listener) {

        this.listener = listener;
    }

    public void onClick(View v) {

        if (listener != null){

            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNumeroPLanta, mProvincia, mLocalidad, mNumeroVisitas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNumeroPLanta = itemView.findViewById(R.id.cardViewPlantaNumero);
            mProvincia = itemView.findViewById(R.id.cardViewPlantaProvincia);
            mLocalidad = itemView.findViewById(R.id.cardViewPlantaLocalidad);
            mNumeroVisitas = itemView.findViewById(R.id.cardViewPlantaCantidadInspecciones);
        }
    }
}
