package com.example.reportesv2;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    public static String coordenadaLat;
    public static String coordenadaLon;
    //---------------Variables coordenadas-----------
    private LocationManager nLocationManager;//variable para localizacion
    private String TAG = "LocalizacionAPP"; //variable para texto de permisos
    public String enviaLatitud = "", enviaLongitud = "";
    private final int REQUEST_ACCESS_FINE =0;
    private final int REQUEST_ACCESS_READ= 0;
    private final int REQUEST_ACCESS_WRITE= 0;
    private final int REQUEST_ACCESS_CAMERA = 0 ;
    //variables para el AlertDialog de la activacion del gps
    AlertDialog alert = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUEST_ACCESS_CAMERA);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_ACCESS_FINE);



        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_ACCESS_READ);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_ACCESS_WRITE);




        //--------------------- asignando varible a objetos------
        nLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //--------------------------------------------------------

        //------------------Evalua si el GPS esta activado, en caso contrario manda a activarlo
        if ( !nLocationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
        }
        //--------------------------------------------------------

        //-------------------- al momento de iniciar la aplicacion cargar las coordenadas
       if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            Log.d(TAG, "Faltan Persmisos");
            return;
        }




        nLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 01, 01, this);
        nLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0l, 01, this);
        nLocationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 01, 01, this);
        //-------------------------------------------------------------------

    }

    @Override //muestra mensaje de acceso consedido o no
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == REQUEST_ACCESS_FINE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permiso ubicación Concedida", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Permiso ubicación denegada", Toast.LENGTH_SHORT).show();
        }
        if(requestCode == REQUEST_ACCESS_CAMERA){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permiso ubicación Concedida", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Permiso ubicación denegada", Toast.LENGTH_SHORT).show();
        }

    }

    //----- METODO para mostrar mensaje de activacion del GPS
    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado. Debe estar activado para obtener la ubicación exacto del reporte.")
                .setCancelable(false)
                .setPositiveButton("ACTIVAR", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }
    //----------------------------------------------

    @Override
    public void onLocationChanged(Location location) {

        //almacena las corrdenadas para enviarlas
        enviaLatitud = location.getLatitude()+"";
        enviaLongitud = location.getLongitude()+"";

        coordenadaLat = enviaLatitud;
        coordenadaLon = enviaLongitud;

        Log.d("Latitud:",enviaLatitud);
        Log.d("Longitud:",enviaLongitud);

        //------------------------------------------
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getApplicationContext(),"Haz desactivado el GPS",Toast.LENGTH_LONG).show();
    }
    //---------------- al cerrar la aplicacion ------------------------
    @Override
    protected void onDestroy() {
        nLocationManager.removeUpdates(this);
        super.onDestroy();
    }
    //---------------------------------------------------------------
}
