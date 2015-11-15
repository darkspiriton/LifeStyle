package com.tesis2.lifestyle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by StOn on 13/11/2015.
 */
public class LogroAdapter extends ParseQueryAdapter<ParseObject> {

    public LogroAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {

                ParseUser currentUser = ParseUser.getCurrentUser();
                ParseQuery<ParseObject> query = currentUser.getRelation("logros").getQuery();
                query.orderByDescending("createdAt");

                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.logro_list_item, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image

         ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.iconLogro);
         ParseFile imageFile = object.getParseFile("imagen");
         if (imageFile != null) {
         todoImage.setParseFile(imageFile);
         todoImage.loadInBackground();
         }


        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.listaTituloLogro);
        titleTextView.setText(object.getString("nombre"));

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.listaDescripcionLogro);
        titleTextView2.setText(object.getString("descripcion"));

        // Add the title view
        TextView titleTextView3 = (TextView) v.findViewById(R.id.listaPuntosLogro);
        titleTextView3.setText(Integer.toString(object.getInt("puntos")));

        // Add the title view
        TextView titleTextView4 = (TextView) v.findViewById(R.id.listaFechaLogro);
        DateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = fecha2.format(object.getCreatedAt());
        titleTextView4.setText(convertido);

        return v;
    }
}
