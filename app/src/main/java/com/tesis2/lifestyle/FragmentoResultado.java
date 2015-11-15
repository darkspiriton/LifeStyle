package com.tesis2.lifestyle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;




public class FragmentoResultado extends Fragment {
    private TextView porcentaje,puntos,riesgo,descripcion;
    private ListView historial;
    private HistorialAdapter historialAdapter;

    public FragmentoResultado() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_resultado, container, false);

        if(view != null){

            //Provee la informacion y tambien genera los views
            historialAdapter = new HistorialAdapter(getContext());

            porcentaje = (TextView) view.findViewById(R.id.tvResultadoDetallePorcentaje);
            riesgo = (TextView) view.findViewById(R.id.tvDetalleResumenRiesgoNivel);
            puntos = (TextView) view.findViewById(R.id.tvResultadoDetallePunto);
            descripcion = (TextView) view.findViewById(R.id.tvResultadoResumenDetalle);

            //lista donde se puesta los datos
            historial = (ListView) view.findViewById(R.id.tvResultadoHistorialDetalle);

            //relacionar el adaptador con la lista
            historial.setAdapter(historialAdapter);
            historialAdapter.loadObjects();



        }


       return view;


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new GetDataTask().execute();

    }

    //El Fragment ha sido quitado de su Activity y ya no está disponible
    @Override
    public void onDetach() {
        super.onDetach();

    }


    private void obtenerHistorialRiesgoOnline() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Risk");
        query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
        query.orderByDescending("createdAt");
        query.fromLocalDatastore();

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject m, ParseException e) {
                if (m == null) {


                } else {
                    puntos.setText("Puntos obtenidos en la evaluación: "+Integer.toString(m.getInt("result"))+" puntos");
                    riesgo.setText("Nivel de Riesgo: "+Integer.toString(m.getInt("riskLevel")));
                }
            }
        });

    }

    private void obtenerRiesgoOnline(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("RiskLevel");
        query.whereEqualTo("level", ParseUser.getCurrentUser().getInt("riskLevel"));
        query.orderByDescending("createdAt");
        query.fromLocalDatastore();
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject m, ParseException e) {
                if (m == null) {

                } else {
                    porcentaje.setText("Probabilidad de adquirir Diabetes: "+m.getString("percentage"));
                    descripcion.setText(m.getString("description"));
                }
            }
        });

    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // Simulates a background job.
            obtenerHistorialRiesgoOnline();
            obtenerRiesgoOnline();
            return "Termino" ;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
        }
    }
}
