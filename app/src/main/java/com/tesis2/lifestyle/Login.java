package com.tesis2.lifestyle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends Activity implements View.OnClickListener {
    Button butLogin;
    EditText user,pass;
    TextView butRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Leer datos
        butLogin = (Button) findViewById(R.id.butLogin);
        butRegister = (TextView) findViewById(R.id.butRegister);
        user = (EditText) findViewById(R.id.etUsuario);
        pass = (EditText) findViewById(R.id.etPass);

        //Monitorear botones
        butLogin.setOnClickListener(this);
        butRegister.setOnClickListener(this);

        butLogin.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    butLogin.setBackgroundColor(Color.parseColor("#4bb53f"));
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    butLogin.setBackgroundColor(Color.parseColor("#C04BB53F"));
                }
                return false;
            }

        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.butLogin){

            String passAux=pass.getText().toString().trim();
            String userAux=user.getText().toString().trim().toLowerCase();

            ParseUser.logInInBackground(userAux, passAux, new LogInCallback() {
                public void done(ParseUser userAux, ParseException e) {
                    if (userAux != null) {
                        butLogin.setEnabled(false);
                        Intent intent = new Intent(Login.this,DispatchActivity.class);
                        startActivity(intent);


                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "El usuario o la contraseña son incorrectos";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }
                }
            });

        }if (view.getId()==R.id.butRegister) {
            Intent intent = new Intent(this,Registro.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
