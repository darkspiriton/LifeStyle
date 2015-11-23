package com.tesis2.lifestyle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by StOn on 22/11/2015.
 */
public class TemaAdapter extends ParseQueryAdapter<ParseObject> {

    public TemaAdapter(Context context, int i) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
                query.orderByAscending("create");
                return query;

            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.foro_list_item, null);
        }

        super.getItemView(object, v, parent);


        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.listaTituloForo);
        titleTextView.setText(object.getString("nombre").toUpperCase());

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.listaDescripcionForo);
        titleTextView2.setText(object.getString("descripcion"));

        // Add the title view
        TextView titleTextView3 = (TextView) v.findViewById(R.id.listaCantidadForo);
        titleTextView3.setText("#"+Integer.toString(object.getInt("cantidad")));

        return v;
    }
}
