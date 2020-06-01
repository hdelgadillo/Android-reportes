package com.example.jess.reportes;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jess.reportes.clases.Preferencias;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private Button btnConectar;
    private EditText usuario;
    private EditText contraseña;

    private CheckBox cbConexion;

    private Boolean checkBoxActivado;



    RequestQueue Respuesta;
    StringRequest solicitudString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnConectar = (Button) findViewById(R.id.btnConectar);
        usuario = (EditText) findViewById(R.id.tbUsuario);
        contraseña = (EditText) findViewById(R.id.tbContra);

        cbConexion = (CheckBox) findViewById(R.id.cbRecordar);

        checkBoxActivado = cbConexion.isChecked(); //asignar el valor de chekbox a una variable

        // esto es para obtener el estado del checkBox para la conexion - pasa directamente al menu principal
        if(Preferencias.obtenerPreferenciaBoolean(this,Preferencias.PREFERENCIA_ESTADO_BOTON_CHECKBOX)){
            Intent entrar = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(entrar); //estas 2 lineas hacen que se habra otro Activity o Ventana en este caso el Menu de inicio

            finish(); //termina la ventana de LOGIN
        }
        //-------------

        //-----boton para enviar datos de usuario y contraseña
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String user = null;
               user = usuario.getText().toString();

               String pass = null;
               pass = contraseña.getText().toString();



               enviarDatos(user, pass);


            }
        });
        //-------fin boton enviar datos

        //-----boton de recodar Conexion
        cbConexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxActivado){
                    cbConexion.setChecked(false);
                }
                checkBoxActivado = cbConexion.isChecked(); //asignar el valor de chekbox a una variable
            }
        });
        //---------finm boton recodar conexion
    }

    //-------metodo para enviar los datos mediante POST
    private void enviarDatos(final String usuario, final String contraseña) {
        //String url="http://reportesv2.infinit.com.mx/consultaUsuarios.php";//servidor remoto
        String url="http://jre.geoportal.mx/consultaUsuarios.php";//servidor remoto


        solicitudString = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equalsIgnoreCase("existe")){
                        Preferencias.guardarPreferenciaBoolean(login.this,cbConexion.isChecked(),Preferencias.PREFERENCIA_ESTADO_BOTON_CHECKBOX); //para llamar al metodo que recuerda la conexiond el ususario

                        Preferencias.guardarPreferenciaString(login.this, usuario,Preferencias.PREFERENCIA_USUARIO_LOGIN);//Para guarar el usuario

                        Intent entrar = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(entrar); //estas 2 lineas hacen que se habra otro Activity o Ventana en este caso el Menu de inicio

                        finish(); //termina la ventana de LOGIN

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"NO SE ENVIARON LOS DATOS: "+response.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.DEBUG = true;

                Toast.makeText(getApplicationContext(),"No se pudo conectar",Toast.LENGTH_SHORT).show();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "Communication Error!", Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "Authentication Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "Server Side Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();

                }

            }




        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //asignando los datos a los parametros
                String user = usuario;
                String password = contraseña;

                //--alimentando los parametros que se enviaran por metodo POST
                Map<String,String> parametros = new HashMap<>();
                parametros.put("usuario",user);
                parametros.put("contra",password);;

                return parametros;
            }
        };
        Respuesta = Volley.newRequestQueue(getApplicationContext());
        Respuesta.add(solicitudString);
        //------------++++++++++++++++++--------fin metodo POST---------+++++++++++++++++--------------*/
    }
    //------------fin metodo enviarDatos

}
