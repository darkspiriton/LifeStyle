package com.tesis2.lifestyle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ResultActivity extends AppCompatActivity {

    TextView resultado,porcentaje,puntos,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        obtenerResultado();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, MenuActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
