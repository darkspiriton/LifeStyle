package com.tesis2.lifestyle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class FragmentoPerfil extends Fragment implements View.OnClickListener {
    private TextView nombre,correo,puntos,puesto;
    private CircleImageView foto;
    private ImageView logro1,logro2,logro3,logro4,butLogros,butRanking;
    private LinearLayout ranking1,ranking2;
    private Button butEditar;
    public FragmentoPerfil() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_perfil, container, false);
        if(view != null){
            nombre = (TextView) view.findViewById(R.id.nombrePerfil);
            correo = (TextView) view.findViewById(R.id.correoPerfil);
            puntos = (TextView) view.findViewById(R.id.tvPuntosDetalle);
            foto = (CircleImageView) view.findViewById(R.id.fotoPerfil);
            logro1 = (ImageView) view.findViewById(R.id.ivLogroImagen1);
            logro2 = (ImageView) view.findViewById(R.id.ivLogroImagen2);
            logro3 = (ImageView) view.findViewById(R.id.ivLogroImagen3);
            logro4 = (ImageView) view.findViewById(R.id.ivLogroImagen4);
            ranking1 = (LinearLayout) view.findViewById(R.id.llRanking1);
            ranking2 = (LinearLayout) view.findViewById(R.id.llRanking2);
            puesto = (TextView) view.findViewById(R.id.tvRankingPuesto2);
            butEditar = (Button) view.findViewById(R.id.butEditar);
            butLogros = (ImageView) view.findViewById(R.id.ivButLogro);
            butRanking = (ImageView) view.findViewById(R.id.ivButRanking);

            butEditar.setOnClickListener(FragmentoPerfil.this);
            butLogros.setOnClickListener(FragmentoPerfil.this);
            butRanking.setOnClickListener(FragmentoPerfil.this);

        }


        new GetDataTask().execute();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       mostrarPerfil();
    }


    //El Fragment ha sido quitado de su Activity y ya no está disponible
    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void mostrarPerfil(){
        ParseUser currentUser = ParseUser.getCurrentUser();

        nombre.setText(currentUser.getString("firstName")+" "+currentUser.getString("lastName"));
        correo.setText(currentUser.getEmail());
        puntos.setText(Integer.toString(currentUser.getInt("puntos")));

        ParseFile imagen = currentUser.getParseFile("photo");
        if (imagen != null) {
            imagen.getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        foto.setImageBitmap(bitmap);
                    } else {
                        // something went wrong
                    }
                }
            });
        }
    }

    private void logroOnline(){

        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query = currentUser.getRelation("logros").getQuery();
        query.orderByDescending("createdAt");
        // Query for new results from the network.
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> m, ParseException e) {
                if (m != null){
                    mostrarLogro(m);
                }

            }
        });

    }

    private void rankingOnline(){



        final ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.orderByDescending("puntos");
        query.setLimit(10);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    int i=0;
                    boolean encontrado = false;
                    while( i < objects.size()){

                        if (currentUser.getUsername()==objects.get(i).getUsername()){

                            ranking1.setVisibility(View.VISIBLE);
                            puesto.setText(i+1 + " PUESTO");
                            i=objects.size();
                            encontrado=true;
                        }
                        i++;
                    }
                    if (encontrado==false) {
                        ranking2.setVisibility(View.VISIBLE);
                    }
                } else {
                    Context context = getContext();
                    CharSequence text = "No se encontro tabla de posiciones";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    private void mostrarLogro(List<ParseObject> m){
        int i=0;
        ParseFile imagen;
        while( i < m.size()){

            if(i<=4){
                switch (i){
                    case 0:
                        imagen =  m.get(i).getParseFile("imagen");
                        if (imagen != null) {
                            imagen.getDataInBackground(new GetDataCallback() {
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        logro1.setImageBitmap(bitmap);
                                    } else {
                                        // something went wrong
                                    }
                                }
                            });
                        }
                        i++;
                        break;
                    case 1:
                        imagen =  m.get(i).getParseFile("imagen");
                        if (imagen != null) {
                            imagen.getDataInBackground(new GetDataCallback() {
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        logro2.setImageBitmap(bitmap);
                                    } else {
                                        // something went wrong
                                    }
                                }
                            });
                        }
                        i++;
                        break;
                    case 2:
                        imagen =  m.get(i).getParseFile("imagen");
                        if (imagen != null) {
                            imagen.getDataInBackground(new GetDataCallback() {
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        logro3.setImageBitmap(bitmap);
                                    } else {
                                        // something went wrong
                                    }
                                }
                            });
                        }
                        i++;
                        break;
                    case 3:
                        imagen =  m.get(i).getParseFile("imagen");
                        if (imagen != null) {
                            imagen.getDataInBackground(new GetDataCallback() {
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        logro4.setImageBitmap(bitmap);
                                    } else {
                                        // something went wrong
                                    }
                                }
                            });
                        }
                        i++;
                        break;

                }
            }else{
                i=m.size();
            }

        }
    }

    @Override
    public void onClick(View view) {
        Context context;

        if(view.getId()==R.id.butEditar){
            context = getContext();
            CharSequence text = "Aqui se podra editar el nombre y correo";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }if (view.getId()==R.id.ivButLogro){
            context = getContext();
            CharSequence text = "Aqui se podra visualizar el detalle de tus logros";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } if (view.getId()==R.id.ivButRanking){
            context = getContext();
            CharSequence text = "Aqui se podra visualizar el detalle del Ranking";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // Simulates a background job.
            logroOnline();
            rankingOnline();
            return "Termino" ;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
        }
    }
}
