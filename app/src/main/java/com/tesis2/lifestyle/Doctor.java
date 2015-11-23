package com.tesis2.lifestyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.ParseObject;

public class Doctor extends AppCompatActivity {
    DoctorAdapter doctorAdapter;
    ListView doctor;
    static String aux;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //lista donde se puesta los datos
        doctor = (ListView) findViewById(R.id.lvDoctores);

        if( getIntent().getStringExtra("categoria") != null){
            String categoria = getIntent().getStringExtra("categoria");
            aux=categoria;
            doctorAdapter = new DoctorAdapter(this,Integer.parseInt(categoria));

            //relacionar el adaptador con la lista
            doctor.setAdapter(doctorAdapter);
            doctorAdapter.loadObjects();
        } else {
            doctorAdapter = new DoctorAdapter(this,Integer.parseInt(aux));

            //relacionar el adaptador con la lista
            doctor.setAdapter(doctorAdapter);
            doctorAdapter.loadObjects();
        }

        doctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ParseObject object = (ParseObject) doctor.getItemAtPosition(position);

                Intent intent = new Intent();
                intent.putExtra("idDoctor", object.getObjectId());
                intent.setClass(Doctor.this, DetalleDoctor.class);
                startActivityForResult(intent,1);
            }
        });




    }




}
