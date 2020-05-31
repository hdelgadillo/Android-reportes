package com.example.reportesv2.ventanas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.reportesv2.MainActivity;
import com.example.reportesv2.R;
import com.example.reportesv2.clases.enviarReportes;
import com.example.reportesv2.clases.seleccionItemReporte;
import com.example.reportesv2.clases.redimencionarImagenBitmap;
import com.example.reportesv2.clases.convercionBitmapString;
import com.example.reportesv2.clases.cambioIconosFotos;
import com.example.reportesv2.clases.ventanasDialogo;


import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class frag_reportes extends Fragment {


    //---variables para el subproceso de recargar label´s de las coordenadas
    public static final long PERIODO = 3000;
    Handler handler;
    Runnable runnable;
    //---------------------------------------------------------------------

    public String txt_lat, txt_lon;//variables para almacenar las coordenadas en string y poder enviarlas al webservice
    public TextView tvLat, tvLon;//variables para mostrar las coordenadas en la app
    Spinner listaReportes_spinner;//variables para almacenar la lista plegable
    Button boton_enviar_reporte;//variables para boton ENVIAR datos
    ImageButton foto1, foto2, foto3; //variables para botones de tomar fotos
    String opcion;//encargado de traer el valor de la opcion que se tome de tipo de reporte(se utiliza para enviar a la clase de enviarReporte

    //--variables para fotografias
    static final String CARPETA_PRINCIPAL = "imgReportes/";
    static final String CARPETA_IMAGEN = "Reportes";
    static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN; //ruta de la imagen en el movil

    String path = "";//variable donde se almacenará la ruta de la imagen ya en el proceso de toma de foto
    File fileImagen=null; //variable para crear el archivo que almacena la fotografia
    Intent intent=null; //variable de tipo Intent para abrir la camara
    Bitmap imageBitmap=null;//variable que almacena la fotografia con la cual sepodra redireccionar y convertir a string
    String string_foto1="",string_foto2="",string_foto3="";//variables que almacenaran la fotografia en forma de string para enviar al webservice
    //--------------f



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_frag_reportes2, container, false);//variable para mostrar contenido al cargar el fragment

        //--- enlazando objetos del XML a variables
        listaReportes_spinner = vista.findViewById(R.id.lista_desplegable_reportes);
        boton_enviar_reporte = vista.findViewById(R.id.btn_enviarRreporte);

        tvLat = vista.findViewById(R.id.latitudVer);
        tvLon = vista.findViewById(R.id.longitudVer);

        foto1 = vista.findViewById(R.id.btn_foto_1);
        foto2 = vista.findViewById(R.id.btn_foto_2);
        foto3 = vista.findViewById(R.id.btn_foto_3);


        //-----------------------------------------

        final String lista_reportes_string[]={"Selecciona...","Poste","Medidor","Frente de Calle"}; //Lista que se mostraran en el Spinner

        ArrayAdapter<String> adaptador_lista_reportes = new ArrayAdapter<String>(getContext(),R.layout.spinner_lista_custom,lista_reportes_string);//Adaptador que asigna mi lista dentro del spinner
        listaReportes_spinner.setAdapter(adaptador_lista_reportes);//asigna_todo el adaptador a el spinner

        //---- Casos cuando se selecciona un item de la lista
        listaReportes_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //metodo que esté escuchando cuando se seleccione un item de la lista de reportes

               //_________________________CLASE en donde se evaluan los items selenccionados________________________
                seleccionItemReporte seleccion_Item_Reporte = new seleccionItemReporte();
                //enviando parametros CONTEXTO - VISTA - VALOR DEL ITEM
                opcion = seleccion_Item_Reporte.mostrar_mensaje( getContext(), vista, lista_reportes_string[position],string_foto1);
                //__________________________________________________________________________________________________
            }//-- fin onItemSelected--

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//metodo en caso de no tener elemento seleccionado
                Toast.makeText(getContext(),"No hay nada que seleccionar",Toast.LENGTH_SHORT).show();
            }//-- fin onNothingSelected--
        });//--fin del metodo para obtener la lista
        //---------------------------------------------------

        //---------funcion del boton "enviar"
        boton_enviar_reporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarReportes enviar = new enviarReportes();
                enviar.metodoEnviarReportes(getContext(),vista,opcion);
            }
        });
        //-----------------------------

        //-- aqui es donde se genera el bucle para obtener las coordenadas del MainActivity
        handler = new Handler();
        runnable = new Runnable(){
            @Override
            public void run(){
                        handler.postDelayed(this, PERIODO);
                        txt_lat = MainActivity.coordenadaLat;
                        txt_lon = MainActivity.coordenadaLon;
                        tvLat.setText(txt_lat);
                        tvLon.setText(txt_lon);
            }
        };
        handler.postDelayed(runnable, PERIODO);
        //---------------------------------------------

//---------BOTONES PARA LAS FOTOS DE LOS REPORTES------------------
        foto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara(1);//abre la camara para la primera foto
            }
        });

        foto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara(2);//abre la camara para la segunda foto
            }
        });

        foto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara(3);//abre la camara para la tercera foto
            }
        });
//------------------fin de botones fotos ------------------------------
        return vista;
    }

    // ---------Metodo para tomar fotografia
    private void abrirCamara(Integer numFoto) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        boolean foto_existe=false;

                //evaluamos si existe las fotografias
                if(string_foto1 != "" && numFoto==1){foto_existe=true;}
                if(string_foto2 != "" && numFoto==2){foto_existe=true;}
                if(string_foto3 != "" && numFoto==3){foto_existe=true;}


                //si no hay fotografia entonces entramos al metodo if
                if(foto_existe == false) {//if experimental

                    Log.i("CAMARA " + numFoto.toString(), "ABRIENDO CAMARA PARA FOTO " + numFoto.toString());//texto para consola
                    //crea el archivo de la foto
                    File miFile = new File(Environment.getExternalStorageDirectory(), DIRECTORIO_IMAGEN);
                    boolean isCreada = miFile.exists();

                    path = "";
                    fileImagen = null;
                    intent = null;

                    if (isCreada == false) {
                        isCreada = miFile.mkdirs(); //si no existe el directorio entonces lo crea
                    }//fin if

                    if (isCreada == true) {
                        //crea el nombre para la fofografia
                        Long consecutivo = System.currentTimeMillis() / 1000;
                        String nombre_foto = "Img_" + consecutivo.toString() + "_" + numFoto.toString() + ".jpg";

                        //crea el directorio de la fotografia co su nombre
                        path = Environment.getExternalStorageDirectory() + File.separator + DIRECTORIO_IMAGEN + File.separator + nombre_foto;//indicamos la ruta de almacenamiento

                        fileImagen = new File(path);//se agrega

                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//toma la fotografia
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));//la inserta en la direccion

                        startActivityForResult(intent, 20);//envia datos al metodo onActivityResult
                    }//fin if
                }//if foto_existe
                 else
                 {
                    Toast.makeText(getContext(),"Esta fotografía ya ha sido tomada",Toast.LENGTH_SHORT).show();
                 }//fin else
    }
    // ---------Fin del metodo para tomar fotografia----------------

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        String texto_confirmacion_cambio_iconos = "";//variable que solo me sirve para visualizar en la consola si se realizo el cambio de iconos de los botones de fotos

        switch (requestCode) {
            case 20:
                //genera el scaner de la fotografia
                MediaScannerConnection.scanFile(getContext(), new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.d("Path", "" + path);
                    }
                });
                imageBitmap = null;
                imageBitmap = BitmapFactory.decodeFile(path);//guarda la foto en un bitmap para posteriormente convertirlo en string
                break;
        }//fin swtich

        if (imageBitmap != null) {
            Log.w("FOTO", "Se ha tomado la fotografia");//mensaje se muestra solo si la fotografia se ha tomado
            imageBitmap = redimencionarImagenBitmap.redimensionarImagen(imageBitmap, 800, 600);//refirecciona el bitmap llamando al metodo publico de la clase redimencionarImageBitmap

            if (string_foto1 == "") {//evalua si el string esta vacio
                try {
                    string_foto1 = convercionBitmapString.convertirImgString(imageBitmap);//transforma el bitmap a string con el metodo publico de la clase conversionBitmapString
                    texto_confirmacion_cambio_iconos = cambioIconosFotos.cambioIconosFotos(10, foto1, foto2, foto3);//cambia el estado de los iconos y guarda como texto el resultado
                    Log.w("CONFIRMACION", texto_confirmacion_cambio_iconos);//muestra el resultado del cambio de iconos
                } catch (Exception e) {
                    Log.e("ERROR-CONVERSION", "NO SE PUDO CONVERTIR: " + e.toString());
                }//fin catch
            }//fin if
            else {
                if (string_foto2 == "") {
                    try {
                        string_foto2 = convercionBitmapString.convertirImgString(imageBitmap);//transforma el bitmap a string con el metodo publico de la clase conversionBitmapString
                        texto_confirmacion_cambio_iconos = cambioIconosFotos.cambioIconosFotos(20, foto1, foto2, foto3);//cambia el estado de los iconos y guarda como texto el resultado
                        Log.w("CONFIRMACION", texto_confirmacion_cambio_iconos);//muestra el resultado del cambio de iconos
                    } catch (Exception e) {
                        Log.e("ERROR-CONVERSION", "NO SE PUDO CONVERTIR: " + e.toString());
                    }//fin catch
                }//fin if
                else {
                    if (string_foto3 == "") {
                        try {
                            string_foto3 = convercionBitmapString.convertirImgString(imageBitmap);//transforma el bitmap a string con el metodo publico de la clase conversionBitmapString
                            texto_confirmacion_cambio_iconos = cambioIconosFotos.cambioIconosFotos(30, foto1, foto2, foto3);//cambia el estado de los iconos y guarda como texto el resultado
                            Log.w("CONFIRMACION", texto_confirmacion_cambio_iconos);//muestra el resultado del cambio de iconos
                        } catch (Exception e) {
                            Log.e("ERROR-CONVERSION", "NO SE PUDO CONVERTIR: " + e.toString());
                        }//fin catch
                    }//fin if
                }//fin else
            }//fin else
    }//fin if bitmap null
        ventanasDialogo.MostrarProgressDialog(getContext());//muestra un cuadro de carga
    }//-------fin onActivityResult--------------

}
