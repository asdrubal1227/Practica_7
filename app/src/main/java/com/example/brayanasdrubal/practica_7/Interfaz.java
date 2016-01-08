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

public class Interfaz extends AppCompatActivity {
    String dato,dato2,usu,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_interfaz);
    }
    public void face (View v){
        Toast.makeText(Interfaz.this, "Cuidador", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        //savePrefs(v);
        Intent i = new Intent(Interfaz.this, face.class);
        startActivity(i);
        finish();

    }
    public void goo (View v){
        Toast.makeText(Interfaz.this, "Cuidador", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        //savePrefs(v);
        Intent i = new Intent(Interfaz.this, goo.class);
        startActivity(i);
        finish();

    }
    public void reg (View v){
        Toast.makeText(Interfaz.this, "Cuidador", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        //savePrefs(v);
        Intent i = new Intent(Interfaz.this, cuenta.class);
        startActivity(i);
        finish();

    }
    public void inse (View v){
        refeshPrefs();
        Toast.makeText(Interfaz.this, dato, Toast.LENGTH_SHORT).show();
        Toast.makeText(Interfaz.this, dato2, Toast.LENGTH_SHORT).show();
        EditText usuc=(EditText) findViewById(R.id.usuario);
        usu= usuc.getText().toString();
        EditText passc=(EditText) findViewById(R.id.contraseña);
        pass= passc.getText().toString();
        if (usu.equals("") || usu.equals(" ") || pass.equals("") || pass.equals(" ") ){
            Intent i = new Intent(Interfaz.this, Verifica.class);
            startActivity(i);
        } else if(usu.equals(dato) && pass.equals(dato2)){
            SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putString("nombre",dato);
            editor.putInt("entro",1);
            editor.commit();
            Intent i = new Intent(Interfaz.this, MainActivity.class);
            startActivity(i);
            finish();
        }else {

            Intent i = new Intent(Interfaz.this, Verifica.class);
            startActivity(i);
        }
    }
    public void refeshPrefs(){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        dato= prefs.getString("usu","");
        dato2= prefs.getString("password","");

    }
}
