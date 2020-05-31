package com.example.jess.reportes;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.TextView;

import com.example.jess.reportes.clases.Preferencias;

public class MainActivity extends AppCompatActivity {

    private String datoUsuario;
    private TextView tvDatoUsuario;
    private final int REQUEST_ACCESS_FINE =0;
    private final int REQUEST_ACCESS_READ= 0;
    private final int REQUEST_ACCESS_WRITE= 0;
    private final int REQUEST_ACCESS_CAMERA = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED ||
           ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED )
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_ACCESS_CAMERA);




        datoUsuario = Preferencias.obtenerPreferenciaString(this,Preferencias.PREFERENCIA_USUARIO_LOGIN);

        tvDatoUsuario = (TextView) findViewById(R.id.tvUsuario);
        tvDatoUsuario.setText(datoUsuario);

        clickParaBoton1();
        clickParaBoton2();

        clickParaCerrarSession();
    }

    private void clickParaBoton1() {
        CardView btn_Reportar = (CardView) findViewById(R.id.btnReportar);
        btn_Reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_reportar = new Intent(getApplicationContext(), menuReportes.class);
                startActivity(ventana_reportar);
            }
        });
    }

    private void clickParaBoton2() {
        CardView btn_Reportar = (CardView) findViewById(R.id.btnMisReportes);
        btn_Reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_misreportes = new Intent(getApplicationContext(), consultaActivity.class);
                startActivity(ventana_misreportes);
            }
        });
    }

    private void clickParaCerrarSession(){
        TextView btn_Cerrar_Session = (TextView) findViewById(R.id.btnCerrarSesion);
        btn_Cerrar_Session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferencias.guardarPreferenciaBoolean(MainActivity.this, false, Preferencias.PREFERENCIA_ESTADO_BOTON_CHECKBOX);
                Intent iniciarLogin = new Intent(MainActivity.this, login.class);
                startActivity(iniciarLogin);
                finish();
            }
        });
    }
}
