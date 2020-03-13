package com.jpdacruz.appcontrolgranos.interfaces;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public interface InterfaceGeneral {

    Double formatearDecimales(Double numero, Integer numeroDecimales);

    Boolean verificarEditText(EditText editText, String string, View view);

    void informarError(String string, View view);

    Boolean verificarSpinner(Spinner spinner, String string, View view);

}
