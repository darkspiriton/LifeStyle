package com.tesis2.lifestyle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ResultadoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultado,porcentaje,puntos,descripcion;
    Button butMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        obtenerResultado();
        butMenu = (Button) findViewById(R.id.butRegresarMenu);

        butMenu.setOnClickListener(this);
    }

    private void obtenerResultado(){


        resultado = (TextView) findViewById(R.id.tvRiesgo);
        porcentaje = (TextView) findViewById(R.id.porcentaje);
        puntos = (TextView) findViewById(R.id.puntos);
        descripcion = (TextView) findViewById(R.id.descripcion);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("RiskLevel");
        query.whereEqualTo("level", ParseUser.getCurrentUser().getInt("riskLevel"));
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {

                } else {
                    resultado.setText(object.getString("name").toUpperCase());
                    porcentaje.setText(object.getString("percentage").toUpperCase());
                    descripcion.setText(object.getString("description").toUpperCase());
                    resultado.setTextColor(Color.parseColor(object.getString("Color")));

                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}
