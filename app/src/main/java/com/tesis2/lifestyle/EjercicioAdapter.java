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

/**
 * Created by StOn on 15/11/2015.
 */
public class EjercicioAdapter  extends ParseQueryAdapter<ParseObject> {

    public EjercicioAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("HorarioEjercicio");
                query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
                query.whereEqualTo("estado", true);

                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.ejercicio_list_item, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image

        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.iconEjercicio1);
        ParseFile imageFile = object.getParseFile("imagenE1");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }

        ParseImageView todoImage2 = (ParseImageView) v.findViewById(R.id.iconEjercicio2);
        ParseFile imageFile2 = object.getParseFile("imagenE2");
        if (imageFile != null) {
            todoImage2.setParseFile(imageFile2);
            todoImage2.loadInBackground();
        }

        ParseImageView todoImage3 = (ParseImageView) v.findViewById(R.id.iconEjercicio2);
        ParseFile imageFile3 = object.getParseFile("imagenE3");
        if (imageFile != null) {
            todoImage3.setParseFile(imageFile3);
            todoImage3.loadInBackground();
        }

        // Add the title view
        TextView titleTextView1 = (TextView) v.findViewById(R.id.listaTituloEjercicio1);
        titleTextView1.setText(object.getString("nombreE1"));

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.listaDescripcionEjercicio1);
        titleTextView2.setText(object.getString("descripcionE1"));

        // Add the title view
        TextView titleTextView7 = (TextView) v.findViewById(R.id.listaRepeticionEjercicio1);
        titleTextView7.setText(Integer.toString(object.getInt("repeticionE1")));

        // Add the title view
        TextView titleTextView8 = (TextView) v.findViewById(R.id.listaDuracionEjercicio1);
        titleTextView8.setText(Integer.toString(object.getInt("duracionE1")));


        // Add the title view
        TextView titleTextView3 = (TextView) v.findViewById(R.id.listaTituloEjercicio2);
        titleTextView3.setText(object.getString("nombreE2"));

        // Add the title view
        TextView titleTextView4 = (TextView) v.findViewById(R.id.listaDescripcionEjercicio2);
        titleTextView4.setText(object.getString("descripcionE2"));

        // Add the title view
        TextView titleTextView9 = (TextView) v.findViewById(R.id.listaRepeticionEjercicio2);
        titleTextView9.setText(Integer.toString(object.getInt("repeticionE2")));

        // Add the title view
        TextView titleTextView10 = (TextView) v.findViewById(R.id.listaDuracionEjercicio2);
        titleTextView10.setText(Integer.toString(object.getInt("duracionE2")));

        // Add the title view
        TextView titleTextView5 = (TextView) v.findViewById(R.id.listaTituloEjercicio3);
        titleTextView5.setText(object.getString("nombreE3"));

        // Add the title view
        TextView titleTextView6 = (TextView) v.findViewById(R.id.listaDescripcionEjercicio3);
        titleTextView6.setText(object.getString("descripcionE3"));

        // Add the title view
        TextView titleTextView11 = (TextView) v.findViewById(R.id.listaRepeticionEjercicio3);
        titleTextView11.setText(Integer.toString(object.getInt("repeticionE3")));

        // Add the title view
        TextView titleTextView12 = (TextView) v.findViewById(R.id.listaDuracionEjercicio3);
        titleTextView12.setText(Integer.toString(object.getInt("duracionE3")));



        return v;
    }
}
