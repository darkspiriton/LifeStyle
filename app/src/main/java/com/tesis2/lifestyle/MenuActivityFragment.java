package com.tesis2.lifestyle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuActivityFragment extends Fragment {
    TextView resultado;
    public MenuActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_menu, container, false);
        obtenerResultado(rootView);

        return rootView;
    }
    private void obtenerResultado(View rootView){


        resultado = (TextView)rootView.findViewById(R.id.tvRiesgoResumen);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("RiskLevel");
        query.whereEqualTo("level", ParseUser.getCurrentUser().getInt("riskLevel"));
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {

                } else {
                    resultado.setText(object.getString("name").toUpperCase());
                    resultado.setTextColor(Color.parseColor(object.getString("Color")));

                }
            }
        });
    }
}
