package com.example.brayanasdrubal.practica_7;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        gettingHashKey();
    }

    public void gettingHashKey() {
        try{
            PackageInfo info=getPackageManager().getPackageInfo("com.example.brayanasdrubal.practica_7", PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
    }
}
