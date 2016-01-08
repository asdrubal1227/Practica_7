package com.example.brayanasdrubal.practica_7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;


public class face extends AppCompatActivity {
    CallbackManager callbackManager;
    Button share;
    TextView details;
    LoginButton loginButton;
    ProfilePictureView profilePictureView;
    ShareDialog shareDialog;
    String name="";
    int cont=0,n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_face);

        loginButton=(LoginButton)findViewById(R.id.login_button);
        profilePictureView=(ProfilePictureView)findViewById(R.id.picture);
        details=(TextView)findViewById(R.id.details);
        share=(Button)findViewById(R.id.share);

        callbackManager= CallbackManager.Factory.create();

        shareDialog=new ShareDialog(this);
        loginButton.setReadPermissions("public_profile email");
        share.setVisibility(View.INVISIBLE);
        details.setVisibility(View.INVISIBLE);
        profilePictureView.setVisibility(View.INVISIBLE);
        if (AccessToken.getCurrentAccessToken()!=null){
            RequestData();
            share.setVisibility(View.VISIBLE);
            details.setVisibility(View.VISIBLE);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AccessToken.getCurrentAccessToken()!=null){
                    share.setVisibility(View.INVISIBLE);
                    details.setVisibility(View.INVISIBLE);
                    profilePictureView.setProfileId(null);
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ShareLinkContent content = new ShareLinkContent.Builder().build();
                //shareDialog.show(content);
                savePrefs(v);
                Intent i = new Intent(face.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if(AccessToken.getCurrentAccessToken()!=null){
                    RequestData();
                    share.setVisibility(View.VISIBLE);
                    details.setVisibility(View.VISIBLE);
                    profilePictureView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    public void RequestData(){
        GraphRequest request= GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                JSONObject json = response.getJSONObject();
                try {
                    if (json != null) {

                        String text = "<b>Name :</b> " + json.getString("name") + "<br><br><b>Email :</b> " + json.getString("email");
                        details.setText(Html.fromHtml(text));
                        profilePictureView.setProfileId(json.getString("id"));
                        name=json.getString("name");
                        for (int i = 0; i < name.length()-2; i++) {
                            // Es 'palabra'
                            if (name.charAt(i) == ' ') {
                                // Reemplazamos
                                cont = i;
                                break;
                            }
                        }
                        name=name.substring(0,cont);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters= new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void savePrefs(View view){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        //EditText campo= (EditText) findViewById(R.id.editText);
        //String campoStr= campo.getText().toString();
        editor.putString("nombre",name);
        editor.putInt("entro",1);
        //editor.remove("nombre");
        editor.commit();
    }
}
