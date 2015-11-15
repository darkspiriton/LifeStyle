package com.tesis2.lifestyle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;


public class FragmentoInicio extends Fragment implements View.OnClickListener {

    TextView resultado,estado,mensaje, fecha;
    ImageView imagen;
    Button butEjecicio,butDieta;
    Bitmap bm;

    public FragmentoInicio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);

        if (view != null){
            resultado = (TextView) view.findViewById(R.id.tvRiesgoNivel);
            estado = (TextView) view.findViewById(R.id.tvEstadoDetalle);
            mensaje = (TextView) view.findViewById(R.id.tvMensajeDetalle);
            imagen = (ImageView) view.findViewById(R.id.ivRiesgoImagen);
            fecha = (TextView) view.findViewById(R.id.tvFechaRiesgo);
            butEjecicio = (Button) view.findViewById(R.id.butEjercicio);
            butDieta = (Button) view.findViewById(R.id.butDieta);

            butEjecicio.setOnClickListener(FragmentoInicio.this);
            butDieta.setOnClickListener(FragmentoInicio.this);
        }
        new GetDataTask().execute();
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void obtenerFechaOnline(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Risk");
        query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
        query.orderByDescending("createdAt");
        // Query for new results from the network.
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> m, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("fecha", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        if (m != null) {
                            ParseObject.pinAllInBackground("fecha", m);
                            DateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
                            String convertido = fecha2.format(m.get(0).getCreatedAt());
                            fecha.setText(convertido);
                        }

                    }
                });
            }
        });

    }

    private void obtenerFechaLocal(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Risk");
        query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
        query.orderByDescending("createdAt");
        query.fromLocalDatastore();

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject m, ParseException e) {
                if (m == null) {
                    obtenerFechaOnline();
                } else {
                    DateFormat fecha3 = new SimpleDateFormat("dd/MM/yyyy");
                    String convertido = fecha3.format(m.getCreatedAt());
                    fecha.setText(convertido);
                }
            }
        });

    }


    private void obtenerResultadoRiesgoLocal(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("RiskLevel");
        query.whereEqualTo("level", ParseUser.getCurrentUser().getInt("riskLevel"));
        query.fromLocalDatastore();

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    obtenerResultadoRiesgoOnLine();
                    obtenerMensajeOnLine();
                } else {
                    mostrarDatos(object);
                    obtenerMensajeLocal();
                }
            }
        });


    }

    private void obtenerResultadoRiesgoOnLine(){

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("RiskLevel");
        query.whereEqualTo("level", ParseUser.getCurrentUser().getInt("riskLevel"));

        // Query for new results from the network.
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> m, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("riesgo", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("riesgo", m);
                        mostrarDatos(m.get(0));
                    }
                });
            }
        });

    }

    private void mostrarDatos(ParseObject object){

        switch (object.getInt("level")) {

            case 1:
                bm = BitmapFactory.decodeResource(getResources(), R.drawable.nivel_1);
                imagen.setImageBitmap(bm);
                break;
            case 2:
                bm = BitmapFactory.decodeResource(getResources(), R.drawable.nivel_2);
                imagen.setImageBitmap(bm);

                break;
            case 3:
                bm = BitmapFactory.decodeResource(getResources(), R.drawable.nivel_3);
                imagen.setImageBitmap(bm);

                break;
            case 4:
                bm = BitmapFactory.decodeResource(getResources(), R.drawable.nivel_4);
                imagen.setImageBitmap(bm);

                break;
            case 5:
                bm = BitmapFactory.decodeResource(getResources(), R.drawable.nivel_5);
                imagen.setImageBitmap(bm);

                break;
        }

        resultado.setText(object.getString("name").toUpperCase());
        resultado.setTextColor(Color.parseColor(object.getString("Color")));
        estado.setText(object.getString("description"));
        imagen.setBackgroundColor(Color.parseColor(object.getString("Color")));
    }


    private void obtenerMensajeOnLine(){
        Random r1= new Random();
        int i = r1.nextInt(20)+1;
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Mensaje");
        query.whereEqualTo("numero",i);
        // Query for new results from the network.
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> m, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("mensaje", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("mensaje", m);
                        mensaje.setText(m.get(0).getString("mensaje"));
                    }
                });
            }
        });





    }

    private void obtenerMensajeLocal(){

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Mensaje");
        query.fromLocalDatastore();

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    obtenerMensajeOnLine();

                } else {
                    mensaje.setText(object.getString("mensaje"));
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.butEjercicio){
            Context context = getContext();
            CharSequence text = "Ejercicios Recomendados";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent intent = new Intent(getActivity(), Ejercicio.class);
            startActivity(intent);

        }if (view.getId()==R.id.butDieta){
            Context context = getContext();
            CharSequence text = "Dietas Recomendadas";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent intent = new Intent(getActivity(), Dieta.class);
            startActivity(intent);
        }
    }


    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // Simulates a background job.
            obtenerResultadoRiesgoLocal();
            obtenerFechaLocal();
            return "Termino" ;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
        }
    }
}
