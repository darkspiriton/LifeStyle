package com.tesis2.lifestyle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class Dieta extends AppCompatActivity {
    DietaAdapter dietaAdapter;
    ListView dieta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Provee la informacion y tambien genera los views
        dietaAdapter = new DietaAdapter(this);

        //lista donde se puesta los datos
        dieta = (ListView) findViewById(R.id.lvDietas);

        //relacionar el adaptador con la lista
        dieta.setAdapter(dietaAdapter);
        dietaAdapter.loadObjects();
    }
}
