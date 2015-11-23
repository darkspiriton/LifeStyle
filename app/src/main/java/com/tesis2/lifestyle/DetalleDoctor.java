package com.tesis2.lifestyle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetalleDoctor extends AppCompatActivity {
    TextView titulo, acerca, experiencia, contacto;
    CircleImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_doctor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra("idDoctor");

        titulo = (TextView) findViewById(R.id.nombreDetalleDoctor);
        acerca = (TextView) findViewById(R.id.tv1DetalleDoctor);
        experiencia = (TextView) findViewById(R.id.tv2DetalleDoctor);
        contacto = (TextView) findViewById(R.id.tv3DetalleDoctor);
        foto = (CircleImageView) findViewById(R.id.fotoPerfil);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctor");
        query.whereEqualTo("objectId", id);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject doctor, ParseException e) {

                if (e == null) {


                    titulo.setText(doctor.getString("nombre"));
                    acerca.setText(doctor.getString("acerca"));
                    experiencia.setText(doctor.getString("experiencia"));
                    contacto.setText(doctor.getString("contacto"));
                    ParseFile imagen = doctor.getParseFile("foto");

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

                } else {

                }

            }
        });


    }


}
