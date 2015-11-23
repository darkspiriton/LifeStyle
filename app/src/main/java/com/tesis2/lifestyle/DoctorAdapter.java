package com.tesis2.lifestyle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by StOn on 21/11/2015.
 */
public class DoctorAdapter extends ParseQueryAdapter<ParseObject> {

    public DoctorAdapter(Context context, final int num) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctor");
                query.whereEqualTo("categoria",num);
                query.orderByAscending("nombre");
                return query;

            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.doctor_list_item, null);
        }

        super.getItemView(object, v, parent);



        // Add the title view
        TextView titleTextView1= (TextView) v.findViewById(R.id.listaTituloTema);
        titleTextView1.setText(object.getString("nombre").toUpperCase());

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.listaUsuarioTema);
        titleTextView2.setText(object.getString("especialidad"));


        return v;
    }
}