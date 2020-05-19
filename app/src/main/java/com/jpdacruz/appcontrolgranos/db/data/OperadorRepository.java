package com.jpdacruz.appcontrolgranos.db.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jpdacruz.appcontrolgranos.db.clases.Constantes;
import com.jpdacruz.appcontrolgranos.db.clases.Operador;

import java.util.List;

public class OperadorRepository {

    private FirebaseDatabase database;
    private DatabaseReference refOperador;
    LiveData<List<Operador>> allOperadores;
    private static final String TAG = "OperadorRepository";

    OperadorRepository(){

        Log.d(TAG,"iniciardo database");

        database = FirebaseDatabase.getInstance();
        refOperador= database.getReference(Constantes.PATH_OPERADOR);
    }
}
