package com.tesis2.lifestyle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by StOn on 13/11/2015.
 */
public class RankingAdapter extends ParseQueryAdapter<ParseUser> {
    int i=-11;

    public RankingAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseUser>() {
            public ParseQuery create() {

                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.orderByDescending("puntos");
                query.setLimit(10);

                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseUser object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.ranking_list_item, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image

        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.iconRanking);
        ParseFile imageFile = object.getParseFile("photo");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.listaPosicionRanking);
        titleTextView.setText(Integer.toString(i));

        // Add the title view
        TextView titleTextView2 = (TextView) v.findViewById(R.id.listaUserRanking);
        titleTextView2.setText(object.getUsername());

        // Add the title view
        TextView titleTextView3 = (TextView) v.findViewById(R.id.listaPuntosRanking);
        titleTextView3.setText(Integer.toString(object.getInt("puntos")));
        i++;
        return v;
    }
}
