package com.example.reportesv2.clases;

import android.util.Log;

import java.text.SimpleDateFormat;

public class obtenerFecha {
    // METODO PARA OBTENER FECHA Y HROA
    public static String ObtenerFecha(){
        long fechaLong = 0;
        String fechaString ="";

        fechaLong = System.currentTimeMillis();
        SimpleDateFormat formatoTiempo = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss_a");
        fechaString = formatoTiempo.format(fechaLong);
        Log.i("FECHA Y HORA", "SE HA OBTENIDO LA FECHA Y LA HORA EN STRING");
        return fechaString;
    }
}
