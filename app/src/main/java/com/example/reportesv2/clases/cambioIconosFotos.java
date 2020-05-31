package com.example.reportesv2.clases;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.reportesv2.R;

public class cambioIconosFotos {


    public static String cambioIconosFotos(Integer codigo, ImageButton foto1, ImageButton foto2, ImageButton foto3){


        if (codigo == 10) {
            foto1.setBackgroundResource(R.drawable.fondo_boton_foto_ok);
            foto1.setImageResource(R.drawable.icono_foto_ok);

            foto2.setBackgroundResource(R.drawable.fondo_boton_foto);
            foto2.setImageResource(R.drawable.icono_agregar_foto_rojo);
            foto2.setClickable(true);
            Log.w("BOTON - FOTO 1", "Se han cambiado los valores de ImageButton1 y ImageButton2");
        }//fin if
        if (codigo == 20) {

            foto2.setBackgroundResource(R.drawable.fondo_boton_foto_ok);
            foto2.setImageResource(R.drawable.icono_foto_ok);

            foto3.setBackgroundResource(R.drawable.fondo_boton_foto);
            foto3.setImageResource(R.drawable.icono_agregar_foto_rojo);
            foto3.setClickable(true);
            Log.w("BOTON - FOTO 2", "Se han cambiado los valores de ImageButton2 y ImageButton3");
        }//fin if

        if (codigo == 30) {
            foto3.setBackgroundResource(R.drawable.fondo_boton_foto_ok);
            foto3.setImageResource(R.drawable.icono_foto_ok);
            Log.w("BOTON - FOTO 3", "Se han cambiado los valores de ImageButton3");
        }//fin if

        return "SE CAMBIARON LOS VALORES DE LOS BOTONES";
    }


}
