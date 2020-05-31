package com.example.reportesv2.clases;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class convercionBitmapString {
    //--------------------- Metodo para convertir imagen bitmap-----------
    public static String convertirImgString(Bitmap imageBitmap) {
        String imagenConvertidaString ="";

        ByteArrayOutputStream array = new ByteArrayOutputStream();

        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte = array.toByteArray();
        imagenConvertidaString = Base64.encodeToString(imagenByte,Base64.DEFAULT);
        Log.i("CONVERSION A STRING", "SE HA CONVERTIDO LA FOTO A STRING CORRECTAMENTE");
        return imagenConvertidaString;
    }
    //----------------------------------------------------------------
}
