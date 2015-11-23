package com.tesis2.lifestyle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class Tema extends AppCompatActivity {
    ListView tema;
    TemaAdapter temaAdapter;
    static String aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //lista donde se puesta los datos
        tema = (ListView) findViewById(R.id.lvTemas);
/**
        if( getIntent().getStringExtra("tema") != null){
            String tipoTema = getIntent().getStringExtra("tipoTema");
            aux=tipoTema;
            temaAdapter = new TemaAdapter(this,Integer.parseInt(tipoTema));

            //relacionar el adaptador con la lista
            tema.setAdapter(temaAdapter);
            temaAdapter.loadObjects();
        } else {
            temaAdapter = new TemaAdapter(this,Integer.parseInt(aux));

            //relacionar el adaptador con la lista
            tema.setAdapter(temaAdapter);
            temaAdapter.loadObjects();
        }

        tema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ParseObject object = (ParseObject) tema.getItemAtPosition(position);

                Intent intent = new Intent();

                //intent.putExtra("idDoctor", object.getObjectId());

                intent.setClass(Tema.this, Mensaje.class);
                startActivityForResult(intent, 1);
            }
        });

 **/
    }
}
