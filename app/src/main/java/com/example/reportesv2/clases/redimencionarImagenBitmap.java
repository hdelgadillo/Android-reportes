package com.example.reportesv2.clases;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

public class redimencionarImagenBitmap {
    //-----------METODO PARA REDIMENCIONAR IMAGEN ---------------
    public static Bitmap redimensionarImagen(Bitmap imageBitmap, float nuevo_ancho, float nuevo_alto) {
        int ancho = 0;
        int alto = 0;

       ancho = imageBitmap.getWidth();
       alto = imageBitmap.getHeight();

        if(ancho>nuevo_alto || alto>nuevo_alto){
            float escalaAncho = nuevo_ancho/ancho;
            float escalaAlto = nuevo_alto/alto;

            Matrix matrix = new Matrix();
            matrix.postScale(escalaAncho,escalaAlto);
            Log.i("REDIMENSIONAR", "SE HA REDIMENSIONADO LA FOTO CON EXITO");
            return Bitmap.createBitmap(imageBitmap,0,0,ancho,alto,matrix,false);

        }else{
            return imageBitmap;
        }
    }
    //----------------------------fin metodo redimensionar----------------

}
