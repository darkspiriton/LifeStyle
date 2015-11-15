package com.tesis2.lifestyle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class Ejercicio extends AppCompatActivity {
    EjercicioAdapter ejercicioAdapter;
    ListView ejercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Provee la informacion y tambien genera los views
        ejercicioAdapter = new EjercicioAdapter(this);

        //lista donde se puesta los datos
        ejercicio = (ListView) findViewById(R.id.lvEjercicio);

        //relacionar el adaptador con la lista
        ejercicio.setAdapter(ejercicioAdapter);
        ejercicioAdapter.loadObjects();
    }
}
