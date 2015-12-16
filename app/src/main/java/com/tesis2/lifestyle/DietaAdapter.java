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

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Dieta");
                query.whereEqualTo("risk", ParseUser.getCurrentUser().getInt("riskLevel"));
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
        ParseFile imageFile = object.getParseFile("imagen");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }

        // Add the title view
        TextView titleTextView1 = (TextView) v.findViewById(R.id.listaTituloDieta1);
        titleTextView1.setText(object.getString("nombre"));

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.listaDescripcionDieta1);
        titleTextView2.setText(object.getString("descripcion"));


        return v;
    }
}
