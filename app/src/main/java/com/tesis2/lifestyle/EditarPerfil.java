package com.tesis2.lifestyle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class EditarPerfil extends AppCompatActivity implements View.OnClickListener {
    private TextView nombre,apellido,correo;
    private Button butActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombre = (TextView) findViewById(R.id.editarNombre);
        apellido = (TextView) findViewById(R.id.editarApellido);
        correo = (TextView) findViewById(R.id.editarCorreo);
        butActualizar = (Button) findViewById(R.id.butEditarPerfil);

        butActualizar.setOnClickListener(this);


    }


    @Override
    protected void onStart() {
        super.onStart();
        ParseUser currentUser = ParseUser.getCurrentUser();
        nombre.setText(currentUser.getString("firstName"));
        apellido.setText(currentUser.getString("lastName"));
        correo.setText(currentUser.getEmail());

    }

    @Override
    public void onClick(View view) {
        String nombreAux = nombre.getText().toString().trim();
        String apellidoAux = apellido.getText().toString().trim();
        String correoAux = correo.getText().toString().trim();

        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.put("firstName",nombreAux);
        currentUser.put("lastName",apellidoAux);
        currentUser.put("email", correoAux);

        currentUser.saveEventually();



        Context context = getApplicationContext();
        CharSequence text = "Sus datos fueron atualizados correctamente";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();

    }
}
