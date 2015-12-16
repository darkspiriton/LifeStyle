package com.tesis2.lifestyle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tema, menu);
        // ...

        // Define the listener
        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        };

        // Get the MenuItem for the action item
        MenuItem actionMenuItem = menu.findItem(R.id.action_nuevo);

        // Assign the listener to that action item
        MenuItemCompat.setOnActionExpandListener(actionMenuItem, expandListener);

        // Any other things you have to do when creating the options menu…

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_nuevo:

                //Falta comprobar de que se puede realizar la evaluacion
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragmentoGenerico = new FragmentoEvaluar();

                if (fragmentoGenerico != null) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.main_content, fragmentoGenerico)
                            .commit();
                }

                // Setear título actual
                setTitle("Evaluar");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }
}
