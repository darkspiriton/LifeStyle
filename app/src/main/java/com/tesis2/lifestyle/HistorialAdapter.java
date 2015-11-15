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

        // Add and download the image
        /**
        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile imageFile = object.getParseFile("image");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }
         **/

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.text1);
        titleTextView.setText(object.getString("user"));

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.text2);
        titleTextView2.setText(Integer.toString(object.getInt("result")));

        // Add the title view
        TextView titleTextView3 = (TextView) v.findViewById(R.id.text3);
        titleTextView3.setText(Integer.toString(object.getInt("riskLevel")));

        // Add the title view
        TextView titleTextView4 = (TextView) v.findViewById(R.id.text4);
        titleTextView4.setText(Boolean.toString(object.getBoolean("evaluado")));

        // Add the title view
        TextView titleTextView5 = (TextView) v.findViewById(R.id.text5);
        DateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = fecha2.format(object.getCreatedAt());
        titleTextView5.setText(convertido);

        return v;
    }

}