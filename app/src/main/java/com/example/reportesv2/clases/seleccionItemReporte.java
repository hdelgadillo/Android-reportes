package com.example.reportesv2.clases;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.reportesv2.R;
import com.example.reportesv2.ventanas.frag_reportes;

public class seleccionItemReporte {
    public String mostrar_mensaje(Context contexto, View vista, String item_reporte, String primera_foto){

        LinearLayout mostrarPostes, mostrarMedidor, mostrarFrenteCalle;
        Button boton_enviar;
        ImageButton boton_primer_foto;

        mostrarPostes = vista.findViewById(R.id.contenedorPoste);
        mostrarMedidor = vista.findViewById(R.id.contenedorMedidor);
        mostrarFrenteCalle = vista.findViewById(R.id.contenedorFrenteCalle);

        boton_enviar = vista.findViewById(R.id.btn_enviarRreporte);
        boton_primer_foto = vista.findViewById(R.id.btn_foto_1);

        //SWITCH para evaluar el item seleccionado y mostar los elementos correspondientes dependiendo del item
        switch (item_reporte){

            case "Poste":
                mostrarPostes.setVisibility(vista.VISIBLE);
                mostrarMedidor.setVisibility(vista.INVISIBLE);
                mostrarFrenteCalle.setVisibility(vista.INVISIBLE);

                boton_enviar.setBackgroundResource(R.drawable.boton_enviar);
                boton_enviar.setClickable(true);
                if(primera_foto ==""){
                    boton_primer_foto.setBackgroundResource(R.drawable.fondo_boton_foto);
                    boton_primer_foto.setImageResource(R.drawable.icono_agregar_foto_rojo);
                    boton_primer_foto.setClickable(true);
                }

                break;
            case "Medidor":
                mostrarMedidor.setVisibility(vista.VISIBLE);
                mostrarPostes.setVisibility(vista.INVISIBLE);
                mostrarFrenteCalle.setVisibility(vista.INVISIBLE);

                boton_enviar.setBackgroundResource(R.drawable.boton_enviar);
                boton_enviar.setClickable(true);
                if(primera_foto =="") {
                    boton_primer_foto.setBackgroundResource(R.drawable.fondo_boton_foto);
                    boton_primer_foto.setImageResource(R.drawable.icono_agregar_foto_rojo);
                    boton_primer_foto.setClickable(true);
                }
                break;
            case "Frente de Calle":
                mostrarFrenteCalle.setVisibility(vista.VISIBLE);
                mostrarPostes.setVisibility(vista.INVISIBLE);
                mostrarMedidor.setVisibility(vista.INVISIBLE);

                boton_enviar.setBackgroundResource(R.drawable.boton_enviar);
                boton_enviar.setClickable(true);
                if(primera_foto =="") {
                    boton_primer_foto.setBackgroundResource(R.drawable.fondo_boton_foto);
                    boton_primer_foto.setImageResource(R.drawable.icono_agregar_foto_rojo);
                    boton_primer_foto.setClickable(true);
                }
                break;
            default:
                mostrarFrenteCalle.setVisibility(vista.INVISIBLE);
                mostrarPostes.setVisibility(vista.INVISIBLE);
                mostrarMedidor.setVisibility(vista.INVISIBLE);

                boton_enviar.setBackgroundResource(R.drawable.boton_enviar_deshabilitado);
                boton_enviar.setClickable(false);
                if(primera_foto =="") {
                    boton_primer_foto.setBackgroundResource(R.drawable.fondo_boton_foto_desactivado);
                    boton_primer_foto.setImageResource(R.drawable.icono_agregar_foto_deshabilitado);
                    boton_primer_foto.setClickable(false);
                }
                break;
        }//---fin SWITCH---
        return item_reporte;
    }
}
