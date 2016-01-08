package com.example.brayanasdrubal.practica_7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class goo extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private static final String TAG="goo";
    private static final int RC_SIGN_IN=9001;
    private GoogleApiClient mGoogleApiClient;
    public TextView details;
    String name="";
    int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goo);
        details=(TextView) findViewById(R.id.detailsg);

        findViewById(R.id.id_sign_in_button).setOnClickListener(this);
        findViewById(R.id.id_sign_out_button).setOnClickListener(this);

        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_sign_in_button:
                signIn();
                break;
            case R.id.id_sign_out_button:
                savePrefs(v);
                Intent i = new Intent(goo.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>(){
                    @Override
                    public void onResult(Status status){
                        updateUI(false);
                    }
                }
        );

    }

    private void signIn() {
        Intent signInIntent= Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        Log.d(TAG, "handleSignInResult: " + result.isSuccess());
        if (result.isSuccess()){
            GoogleSignInAccount acct=result.getSignInAccount();
            String text = "<b>Name :</b> " + acct.getDisplayName()+ "<br><br><b>Email :</b> " + acct.getEmail();
            name=acct.getDisplayName();
            for (int i = 0; i < name.length()-2; i++) {
                // Es 'palabra'
                if (name.charAt(i) == ' ') {
                    // Reemplazamos
                    cont = i;
                    break;
                }
            }
            name = name.substring(0,cont);
            details.setText(Html.fromHtml(text));
            updateUI(true);
        }else {
            updateUI(false);
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn){
            Toast.makeText(goo.this, "Sesión Iniciada", Toast.LENGTH_LONG).show();
            findViewById(R.id.id_sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.id_sign_out_button).setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(goo.this, "Sesión Terminada", Toast.LENGTH_LONG).show();
            findViewById(R.id.id_sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.id_sign_out_button).setVisibility(View.GONE);
        }
    }

    public void savePrefs(View view){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        //EditText campo= (EditText) findViewById(R.id.editText);
        //String campoStr= campo.getText().toString();
        editor.putString("nombre", name);
        editor.putInt("entro", 1);
        //editor.clear(); //Limpia todas las preferencias
        //editor.remove("nombre");
        editor.commit();
    }
}
