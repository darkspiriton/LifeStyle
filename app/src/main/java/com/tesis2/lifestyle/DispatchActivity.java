package com.tesis2.lifestyle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;

public class DispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Verifica que haya una sesion de usuario activa
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {

            //Si la sesion esta activa lo enviar al Menu de Actividades

            if (currentUser.getBoolean("evaluation") == false){
                startActivity(new Intent(this, RiskWelcomeActivity.class));
            } else {
                startActivity(new Intent(this,MenuActivity.class));
            }

        } else {
            //Si no esta activa lo envia al Login
            startActivity(new Intent(this, Login.class));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
