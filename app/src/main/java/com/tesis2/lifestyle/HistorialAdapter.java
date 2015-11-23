package com.tesis2.lifestyle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HistorialAdapter extends ParseQueryAdapter<ParseObject> {

    public HistorialAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {

                ParseQuery query = new ParseQuery("Risk");
                query.whereEqualTo("user", ParseUser.getCurrentUser().getUsername());
                query.orderByDescending("createdAt");
                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.historial_list_item, null);
        }

        super.getItemView(object, v, parent);

        TextView titleTextView = (TextView) v.findViewById(R.id.text1);
        if (object.getBoolean("evaluado")==true ){
            titleTextView.setText("Evaluado");
        } else {
            titleTextView.setText("Sin Evaluar");
        }

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.text2);
        titleTextView2.setText("Puntos obtenidos: " + Integer.toString(object.getInt("result")));

        // Add the title view
        TextView titleTextView3 = (TextView) v.findViewById(R.id.text3);
        titleTextView3.setText("Nivel de Riesgo obtenido: " + Integer.toString(object.getInt("riskLevel")));

        // Add the title view
        TextView titleTextView4 = (TextView) v.findViewById(R.id.text4);
        titleTextView4.setText("Usuario: " + object.getString("user"));

        // Add the title view
        TextView titleTextView5 = (TextView) v.findViewById(R.id.text5);
        DateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = fecha2.format(object.getCreatedAt());
        titleTextView5.setText(convertido);

        return v;
    }

}