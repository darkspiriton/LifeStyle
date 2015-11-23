package com.tesis2.lifestyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class FragmentoEspecialista extends Fragment {

    EspecialistaAdapter especialistaAdapter;
    ListView categoria;

    public FragmentoEspecialista() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_especialista, container, false);

        //Provee la informacion y tambien genera los views
        especialistaAdapter = new EspecialistaAdapter(getContext());

        //lista donde se puesta los datos
        categoria = (ListView) view.findViewById(R.id.lvDetalleCategoria);

        //relacionar el adaptador con la lista
        categoria.setAdapter(especialistaAdapter);
        especialistaAdapter.loadObjects();

        categoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("categoria", Integer.toString(position+1));
                intent.setClass(getContext(),Doctor.class);
                startActivity(intent);
            }
        });

        return view;


    }

}
