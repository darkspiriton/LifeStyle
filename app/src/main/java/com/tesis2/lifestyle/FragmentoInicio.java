package com.tesis2.lifestyle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class FragmentoInicio extends Fragment {

    TextView resultado,estado,mensaje;
    ImageView imagen;
    Bitmap bm;

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
        estado = (TextView) view.findViewById(R.id.tvEstadoDetalle);
        mensaje = (TextView) view.findViewById(R.id.tvMensajeDetalle);
        imagen = (ImageView) view.findViewById(R.id.ivRiesgoImagen);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("RiskLevel");
        query.whereEqualTo("level", ParseUser.getCurrentUser().getInt("riskLevel"));
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                } else {
                    switch (object.getInt("level")){

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
                    //Sacar numero aleatorio
                    int i=1;
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Consejo");
                    query.whereEqualTo("numero", i);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject m, ParseException e) {
                            mensaje.setText(m.getString("mensaje"));
                        }
                    });

                    resultado.setText(object.getString("name").toUpperCase());
                    resultado.setTextColor(Color.parseColor(object.getString("Color")));
                    estado.setText(object.getString("description"));
                    imagen.setBackgroundColor(Color.parseColor(object.getString("Color")));
                }
            }
        });
    }
}
