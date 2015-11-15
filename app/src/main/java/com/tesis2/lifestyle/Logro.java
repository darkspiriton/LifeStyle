package com.tesis2.lifestyle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class Logro extends AppCompatActivity {
    private LogroAdapter logroAdapter;
    private ListView logro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Provee la informacion y tambien genera los views
        logroAdapter = new LogroAdapter(this);

        //lista donde se puesta los datos
        logro = (ListView) findViewById(R.id.lvDetalleLogro);

        //relacionar el adaptador con la lista
        logro.setAdapter(logroAdapter);
        logroAdapter.loadObjects();
    }
}
