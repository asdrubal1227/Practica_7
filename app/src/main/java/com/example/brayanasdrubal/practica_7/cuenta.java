package com.example.brayanasdrubal.practica_7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class cuenta extends AppCompatActivity{
    EditText campo,campo2,usu;
    String campoStr,campoStr2,usu2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cuenta);
        //refeshPrefs();
        usu= (EditText) findViewById(R.id.usu);
        campo= (EditText) findViewById(R.id.pass);
        campo2= (EditText) findViewById(R.id.pass2);


    }


    public void onClick (View v){
        Toast.makeText(cuenta.this, "Cuidador", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        campoStr= campo.getText().toString();
        campoStr2= campo2.getText().toString();
        if (campoStr.equals(campoStr2)){
            savePrefs(v);
            Intent i = new Intent(cuenta.this, Interfaz.class);
            startActivity(i);
            finish();

        } else {

            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            //Abrir la actividad
            Intent i = new Intent(this, Verifica.class);
            startActivity(i);
        }

    }

    public void savePrefs(View view){
        usu2= usu.getText().toString();
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("usu",usu2);
        editor.putString("password",campoStr2);
        // editor.clear(); //Limpia todas las preferencias
        //editor.remove("nombre");
        editor.commit();
    }

    public void clearPrefs(View view){
       // refeshPrefs();
    }


    /*public void refeshPrefs(){
        TextView info= (TextView) findViewById(R.id.tNombre);
        String dato= prefs.getString("nombre","NA");
        info.setText(dato);

    }*/
}
