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
public class DietaAdapter extends ParseQueryAdapter<ParseObject> {

    public DietaAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("HorarioDieta");
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
            v = View.inflate(getContext(), R.layout.dieta_list_item, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image

        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.iconDieta1);
        ParseFile imageFile = object.getParseFile("imagenDesayuno");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }

        ParseImageView todoImage2 = (ParseImageView) v.findViewById(R.id.iconDieta2);
        ParseFile imageFile2 = object.getParseFile("imagenAlmuerzo");
        if (imageFile != null) {
            todoImage2.setParseFile(imageFile2);
            todoImage2.loadInBackground();
        }

        ParseImageView todoImage3 = (ParseImageView) v.findViewById(R.id.iconDieta3);
        ParseFile imageFile3 = object.getParseFile("imagenCena");
        if (imageFile != null) {
            todoImage3.setParseFile(imageFile3);
            todoImage3.loadInBackground();
        }

        // Add the title view
        TextView titleTextView1 = (TextView) v.findViewById(R.id.listaTituloDieta1);
        titleTextView1.setText(object.getString("desayuno"));

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.listaDescripcionDieta1);
        titleTextView2.setText(object.getString("descripcionDesayuno"));

        // Add the title view
        TextView titleTextView3 = (TextView) v.findViewById(R.id.listaTituloDieta2);
        titleTextView3.setText(object.getString("almuerzo"));

        // Add the title view
        TextView titleTextView4 = (TextView) v.findViewById(R.id.listaDescripcionDieta2);
        titleTextView4.setText(object.getString("descripcionAlmuerzo"));

        // Add the title view
        TextView titleTextView5 = (TextView) v.findViewById(R.id.listaTituloDieta3);
        titleTextView5.setText(object.getString("cena"));

        // Add the title view
        TextView titleTextView6 = (TextView) v.findViewById(R.id.listaDescripcionDieta3);
        titleTextView6.setText(object.getString("descripcionCena"));



        return v;
    }
}
