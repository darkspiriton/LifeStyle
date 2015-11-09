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


public class FragmentoInicio extends Fragment {

    TextView resultado;

    public FragmentoInicio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);

        obtenerResultado(view);

        return view;
    }

    private void obtenerResultado(View view){
        resultado = (TextView) view.findViewById(R.id.tvRiesgoNivel);
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
