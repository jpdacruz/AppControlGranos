package com.jpdacruz.appcontrolgranos.db.interfaces;

import com.jpdacruz.appcontrolgranos.db.clases.Operador;

public interface CallBackInterface {

    void callBackMainActivity(Operador operador);
    void callBackToAddPlanta(Operador operador);
}
