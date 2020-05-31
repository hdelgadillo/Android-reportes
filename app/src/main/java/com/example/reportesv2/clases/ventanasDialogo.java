package com.example.reportesv2.clases;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.app.ProgressDialog;
public class ventanasDialogo {

    //--Metodo Mostrar ProgressDialog
    public static void MostrarProgressDialog(Context contexto){

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(contexto);

        Log.w("ProgressDialog-Abriendo", "Abriendo ProgressDialog");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Guardando fotografía");
        progressDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Log.w("ProgressDialog-Cerrando", "Cerrando ProgressDialog");
            }
        },5000);
    }//fin meotodo MostrarProgressDialog
}
