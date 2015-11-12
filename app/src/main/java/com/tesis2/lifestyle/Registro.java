package com.tesis2.lifestyle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    Button butCreate, butCancel;
    EditText user,name,lastName,email, pass,pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        butCreate=(Button) findViewById(R.id.butCreate);
        butCancel=(Button) findViewById(R.id.butCancel);
        user=(EditText) findViewById(R.id.etUserRegister);
        name=(EditText) findViewById(R.id.etName);
        lastName=(EditText) findViewById(R.id.etLastName);
        email=(EditText) findViewById(R.id.etEmail);
        pass=(EditText) findViewById(R.id.etPassRegister);
        pass2=(EditText) findViewById(R.id.etPass2Register);

        butCreate.setOnClickListener(this);
        butCancel.setOnClickListener(this);

        butCreate.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    butCreate.setBackgroundColor(Color.parseColor("#4bb53f"));
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    butCreate.setBackgroundColor(Color.parseColor("#C04BB53F"));
                }
                return false;
            }

        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.butCreate){
            final String nameAux=name.getText().toString().trim();
            final String lastNameAux=lastName.getText().toString().trim();
            final String userAux=user.getText().toString().trim();
            final String passAux=pass.getText().toString().trim();
            final String passAux2=pass2.getText().toString().trim();
            final String emailAux=email.getText().toString().trim();

            //verificar si son nulos
            if(passAux.equals(passAux2)){
                if (emailAux.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Ingrese un correo valido";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else {


                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Imagenes");
                    query.whereEqualTo("tipo", "foto_perfil");
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e==null){
                                ParseUser usuario = new ParseUser();
                                usuario.setUsername(userAux.toLowerCase());
                                usuario.setEmail(emailAux.toLowerCase());
                                usuario.setPassword(passAux);
                                usuario.put("firstName", nameAux);
                                usuario.put("lastName",lastNameAux);
                                usuario.put("evaluation", false);
                                usuario.put("puntos", 0);
                                usuario.put("photo",object.getParseFile("imagen"));

                                usuario.saveInBackground();

                                usuario.signUpInBackground(new SignUpCallback() {
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            ParseUser.logInInBackground(userAux, passAux, new LogInCallback() {
                                                public void done(ParseUser userAux, ParseException e) {

                                                    butCreate.setEnabled(false);
                                                    startActivity(new Intent(Registro.this, DispatchActivity.class));

                                                }
                                            });
                                        } else {
                                            Context context = getApplicationContext();
                                            CharSequence text = "Los datos de registro estan incorrectos o el usuario ya existe";
                                            int duration = Toast.LENGTH_SHORT;
                                            Toast toast = Toast.makeText(context, text, duration);
                                            toast.show();
                                        }
                                    }
                                });
                            }else{
                                Context context = getApplicationContext();
                                CharSequence text = "No se pudo conectar a internet intentelo de nuevo";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }

                        }
                    });


                }
            }else {
                Context context = getApplicationContext();
                CharSequence text = "Las contraseñas no son iguales";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }else{
            startActivity(new Intent(this, DispatchActivity.class));        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
