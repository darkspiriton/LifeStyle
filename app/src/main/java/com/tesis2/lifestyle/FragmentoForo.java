package com.tesis2.lifestyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class FragmentoForo extends Fragment {
    ForoAdapter foroAdapter;
    ListView foro;

    public FragmentoForo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_foro, container, false);

        //Provee la informacion y tambien genera los views
        foroAdapter = new ForoAdapter(getContext());

        //lista donde se puesta los datos
        foro = (ListView) view.findViewById(R.id.lvForo);

        //relacionar el adaptador con la lista
        foro.setAdapter(foroAdapter);
        foroAdapter.loadObjects();

        foro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                //intent.putExtra("categoria", Integer.toString(position + 1));
                intent.setClass(getContext(), Tema.class);
                startActivity(intent);

            }
        });


        return view;
    }


}
