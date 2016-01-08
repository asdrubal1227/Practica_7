package com.example.brayanasdrubal.practica_7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        refeshPrefs();
    }


    public void refeshPrefs(){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        TextView info= (TextView) findViewById(R.id.textView);
        String dato= prefs.getString("nombre","");
        info.setText("Hola " + dato);

    }

    public void cs(View v){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt("entro",0);
        editor.commit();
        Intent i = new Intent(MainActivity.this, Interfaz.class);
        startActivity(i);
        finish();
    }
}
