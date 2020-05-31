package com.example.reportesv2.clases;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reportesv2.MainActivity;
import com.example.reportesv2.R;

public class enviarReportes {
    public void metodoEnviarReportes(Context contexto, View vista, String opcionReporte){

        EditText tb_numero_poste, tb_propietario_poste, tb_uso_poste, tb_estado_poste,
                 tb_numero_medidor, tb_ubicacion_medidor,
                 tb_calle_frenteCalle, tb_numero_calle_frenteCalle, tb_colonia_frenteCalle;

        String txt_tb_numero_poste, txt_tb_propietario_poste, txt_tb_uso_poste, txt_tb_estado_poste,
                txt_tb_numero_medidor, txt_tb_ubicacion_medidor,
                txt_tb_calle_frenteCalle, txt_tb_numero_calle_frenteCalle, txt_tb_colonia_frenteCalle;


        TextView tvLat, tvLog;

        tb_numero_poste = vista.findViewById(R.id.tb_numero_poste);
        tb_propietario_poste = vista.findViewById(R.id.tb_propietario_poste);
        tb_uso_poste = vista.findViewById(R.id.tb_uso_poste);
        tb_estado_poste = vista.findViewById(R.id.tb_estado_poste);

        tb_numero_medidor = vista.findViewById(R.id.tb_numero_medidor);
        tb_ubicacion_medidor = vista.findViewById(R.id.tb_ubicacion_medidor);

        tb_calle_frenteCalle = vista.findViewById(R.id.tb_calle_frenteCalle);
        tb_numero_calle_frenteCalle = vista.findViewById(R.id.tb_numero_calle_frenteCalle);
        tb_colonia_frenteCalle = vista.findViewById(R.id.tb_colonia_frenteCalle);


        switch (opcionReporte) {
            case "Poste":

                break;
            case "Medidor":
                break;
            case "Frente de Calle":
                break;
            default:
                Toast.makeText(contexto,opcionReporte,Toast.LENGTH_SHORT).show();
                break;
        }


    }
}
