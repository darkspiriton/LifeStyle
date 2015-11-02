package com.tesis2.lifestyle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
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
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.butCreate){
            String nameAux=name.getText().toString().trim();
            String lastNameAux=lastName.getText().toString().trim();
            String userAux=user.getText().toString().trim();
            String passAux=pass.getText().toString().trim();
            String passAux2=pass2.getText().toString().trim();
            String emailAux=email.getText().toString().trim();

            //verificar si son nulos
            if(passAux.equals(passAux2)){
                if (emailAux.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Ingrese un correo valido";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else {
                    ParseUser usuario = new ParseUser();
                    usuario.setUsername(userAux.toLowerCase());
                    usuario.setEmail(emailAux.toLowerCase());
                    usuario.setPassword(passAux);
                    usuario.put("fistName",nameAux);
                    usuario.put("lastName",lastNameAux);
                    usuario.put("evaluation",false);

                    usuario.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                finish();
                            } else {
                                Context context = getApplicationContext();
                                CharSequence text = "Los datos de registro estan incorrectos o el usuario ya existe";
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
            finish();
        }

    }
}
