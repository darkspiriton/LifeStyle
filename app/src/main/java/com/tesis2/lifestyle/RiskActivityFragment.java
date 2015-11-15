package com.tesis2.lifestyle;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;
import java.util.Random;


public class RiskActivityFragment extends Fragment implements View.OnClickListener{

    public static final String ARG_PAGE = "page";

    private int mPageNumber;
    private Button butFin;
    private RadioButton r1_1,r1_2,r1_3,r1_4,r2_1,r2_2,r4_1,r4_2,r4_3,r5_1,r5_2,r6_1,r6_2,r7_1,r7_2,r8_1,r8_2,r9_1,r9_2,r9_3;
    private static int r1=6,r3,r4=6,r5=6,r6=6,r7=6,r8=6,r9=6;
    private int riesgo,riskLevel;
    private static String r2="";
    private static double IMC;
    private static EditText r3_1,r3_2;


    public static RiskActivityFragment create(int pageNumber) {
        RiskActivityFragment fragment = new RiskActivityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RiskActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments()!= null ? getArguments().getInt(ARG_PAGE):1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView=null;
                switch (mPageNumber){
                    case (0):
                        rootView = (ViewGroup) inflater.inflate(
                                R.layout.fragment_risk, container, false);
                        r1_1 = (RadioButton) rootView.findViewById(R.id.r1_1);
                        r1_2 = (RadioButton) rootView.findViewById(R.id.r1_2);
                        r1_3 = (RadioButton) rootView.findViewById(R.id.r1_3);
                        r1_4 = (RadioButton) rootView.findViewById(R.id.r1_4);
                        r2_1 = (RadioButton) rootView.findViewById(R.id.rg1_1);
                        r2_2 = (RadioButton) rootView.findViewById(R.id.r2_2);
                        r3_1 = (EditText) rootView.findViewById(R.id.r3_1);
                        r3_2 = (EditText) rootView.findViewById(R.id.r3_2);

                        r1_1.setOnClickListener(this);
                        r1_2.setOnClickListener(this);
                        r1_3.setOnClickListener(this);
                        r1_4.setOnClickListener(this);
                        r2_1.setOnClickListener(this);
                        r2_2.setOnClickListener(this);

                        return rootView;

                    case (1):
                        rootView = (ViewGroup) inflater.inflate(
                                R.layout.fragment_risk_2, container, false);
                        r4_1 = (RadioButton) rootView.findViewById(R.id.r4_1);
                        r4_2 = (RadioButton) rootView.findViewById(R.id.r4_2);
                        r4_3 = (RadioButton) rootView.findViewById(R.id.r4_3);

                        r5_1 = (RadioButton) rootView.findViewById(R.id.r5_1);
                        r5_2 = (RadioButton) rootView.findViewById(R.id.r5_2);
                        r6_1 = (RadioButton) rootView.findViewById(R.id.r6_1);
                        r6_2 = (RadioButton) rootView.findViewById(R.id.r6_2);

                        r4_1.setOnClickListener(this);
                        r4_2.setOnClickListener(this);
                        r4_3.setOnClickListener(this);

                        r5_1.setOnClickListener(this);
                        r5_2.setOnClickListener(this);
                        r6_1.setOnClickListener(this);
                        r6_2.setOnClickListener(this);

                        return rootView;

                    case (2):
                        rootView = (ViewGroup) inflater.inflate(
                                R.layout.fragment_risk_3, container, false);
                        r7_1 = (RadioButton) rootView.findViewById(R.id.r7_1);
                        r7_2 = (RadioButton) rootView.findViewById(R.id.r7_2);
                        r8_1 = (RadioButton) rootView.findViewById(R.id.r8_1);
                        r8_2 = (RadioButton) rootView.findViewById(R.id.r8_2);
                        r9_1 = (RadioButton) rootView.findViewById(R.id.r9_1);
                        r9_2 = (RadioButton) rootView.findViewById(R.id.r9_2);
                        r9_3 = (RadioButton) rootView.findViewById(R.id.r9_3);

                        r7_1.setOnClickListener(this);
                        r7_2.setOnClickListener(this);
                        r8_1.setOnClickListener(this);
                        r8_2.setOnClickListener(this);
                        r9_1.setOnClickListener(this);
                        r9_2.setOnClickListener(this);
                        r9_3.setOnClickListener(this);

                        butFin = (Button)rootView.findViewById(R.id.butSendE);
                        butFin.setOnClickListener(this);

                        return rootView;
                }
        return rootView;
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.butSendE){
            String pesoAux=r3_1.getText().toString().trim();
            String alturaAux=r3_2.getText().toString().trim();

            if (pesoAux.equals("") || alturaAux.equals("") || r1==6 || r4 ==6 || r5 ==6 || r6==6 || r7==6 || r8==6 || r9==6 || r2.equals("")){
                Context context = view.getContext();
                CharSequence text = "El cuestionario debe estar completo para cancular su nivel de riesgo" ;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else{
                double peso,altura;
                try{
                    peso = Double.parseDouble(pesoAux);
                    altura = Double.parseDouble(alturaAux);

                    IMC=peso/(altura*altura);

                    if (IMC<25 ){
                        r3=0;
                    }
                    if (25<=IMC &&IMC<=30) {
                        r3=1;
                    }
                    if (IMC>30) {
                        r3=3;
                    }

                    riesgo = r1+r3+r4+r5+r6+r7+r8+r9;
                    riskLevel=0;

                    if (riesgo<7 ){
                        riskLevel=1;
                    }
                    if (7<=riesgo &&riesgo<=11) {
                        riskLevel=2;
                    }
                    if (12<=riesgo &&riesgo<=14) {
                        riskLevel=3;
                    }
                    if (14<=riesgo &&riesgo<=20) {
                        riskLevel=4;
                    }
                    if (riesgo>20) {
                        riskLevel=5;
                    }




                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Logro");
                    query.whereEqualTo("indicador", 1);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject logro, ParseException e) {

                            if (e==null){
                                //Metodo que asigne las recomendciones parametro riskLevel
                                //Metodo asyncrono para guardar foto de perfil



                                ParseUser currentUser = ParseUser.getCurrentUser();
                                ParseRelation relation = currentUser.getRelation("logros");

                                int puntosAnteriores=currentUser.getInt("puntos");
                                int puntosNuevos=logro.getInt("puntos");
                                currentUser.put("puntos", puntosAnteriores + puntosNuevos);

                                relation.add(logro);


                                currentUser.put("evaluation", true);
                                currentUser.put("sex", r2);
                                currentUser.put("riskLevel", riskLevel);
                                currentUser.put("estado","sin evaluar");

                                ParseObject risk = new ParseObject("Risk");
                                risk.put("userId",currentUser.getObjectId());
                                risk.put("user",currentUser.getUsername());
                                risk.put("p1",r1);
                                risk.put("p2",r2);
                                risk.put("p3",r3);
                                risk.put("p4",r4);
                                risk.put("p5",r5);
                                risk.put("p6",r6);
                                risk.put("p7",r7);
                                risk.put("p8",r8);
                                risk.put("p9",r9);
                                risk.put("result", riesgo);
                                risk.put("evaluado", false);
                                risk.put("riskLevel", riskLevel);

                                risk.saveInBackground();

                                currentUser.saveInBackground();

                                foto foto= new foto();
                                foto.execute();


                                Intent intent = new Intent(getActivity(),MenuActivity.class);
                                startActivity(intent);


                            }else{

                            }

                        }
                    });




                } catch (Exception e){
                    Context context = view.getContext();
                    CharSequence text = "Los valores de peso y talla no son numericos" ;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }

        } else {
            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.r1_1:
                    if (checked)
                    r1=0;
                    break;
                case R.id.r1_2:
                    if (checked)
                        r1=2;
                    break;
                case R.id.r1_3:
                    if (checked)
                        r1=3;
                    break;
                case R.id.r1_4:
                    if (checked)
                        r1=4;
                    break;
                case R.id.rg1_1:
                    if (checked)
                        r2="Hombre";
                    break;
                case R.id.r2_2:
                    if (checked)
                        r2="Mujer";
                    break;
                case R.id.r4_1:
                    if (checked)
                        r4=0;
                    break;
                case R.id.r4_2:
                    if (checked)
                        r4=3;
                    break;
                case R.id.r4_3:
                    if (checked)
                        r4=4;
                    break;
                case R.id.r5_1:
                    if (checked)
                        r5=0;
                    break;
                case R.id.r5_2:
                    if (checked)
                        r5=2;
                    break;
                case R.id.r6_1:
                    if (checked)
                        r6=0;
                    break;
                case R.id.r6_2:
                    if (checked)
                        r6=1;
                    break;
                case R.id.r7_1:
                    if (checked)
                        r7=2;
                    break;
                case R.id.r7_2:
                    if (checked)
                        r7=0;
                    break;
                case R.id.r8_1:
                    if (checked)
                        r8=5;
                    break;
                case R.id.r8_2:
                    if (checked)
                        r8=0;
                    break;
                case R.id.r9_1:
                    if (checked)
                        r9=0;
                    break;
                case R.id.r9_2:
                    if (checked)
                        r9=3;
                    break;
                case R.id.r9_3:
                    if (checked)
                        r9=5;
                    break;
            }
        }
    }


    private void agregarFotoPerfil(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Imagenes");
        query.whereEqualTo("tipo", r2.toLowerCase());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e==null){
                    ParseUser usuario = ParseUser.getCurrentUser();
                    usuario.put("photo",object.getParseFile("imagen"));
                    usuario.saveInBackground();
                }else{

                }

            }
        });

    }

    private void recomendacionDietaUser(){

        ParseObject dietaFinal = new ParseObject("HorarioDieta");
        dietaFinal.put("estado", true);
        dietaFinal.put("userId", ParseUser.getCurrentUser().getObjectId());
        dietaFinal.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dieta");
        query.whereEqualTo("risk", riskLevel);
        query.whereEqualTo("tipo", "desayuno");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> dietas, ParseException e) {
                if (dietas != null) {

                    Log.d("Dietas", "Retrieved " + dietas.size() + " dietas");

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("HorarioDieta");
                    query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
                    query.whereEqualTo("estado", true);

                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(final ParseObject horario, ParseException e) {
                            if (horario!=null) {
                                Random ram = new Random();
                                int i = ram.nextInt(dietas.size());
                                ParseObject dieta = dietas.get(i);
                                horario.put("desayuno", dieta.getString("nombre"));
                                horario.put("risk", dieta.getInt("risk"));
                                horario.put("descripcionDesayuno", dieta.getString("descripcion"));
                                //horario.put("imagenDesayuno", dieta.getParseFile("imagen"));
                                horario.saveInBackground();
                            }else {

                            }


                        }
                    });

                }

            }
        });

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Dieta");
        query2.whereEqualTo("risk", riskLevel);
        query2.whereEqualTo("tipo", "almuerzo");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> dietas2, ParseException e) {
                if (dietas2 != null) {

                    Log.d("Dietas", "Retrieved " + dietas2.size() + " dietas");

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("HorarioDieta");
                    query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
                    query.whereEqualTo("estado", true);

                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(final ParseObject horario, ParseException e) {
                            if (horario!=null) {
                                Random ram = new Random();
                                int i = ram.nextInt(dietas2.size());
                                ParseObject dieta = dietas2.get(i);
                                horario.put("almuerzo", dieta.getString("nombre"));
                                horario.put("descripcionAlmuerzo", dieta.getString("descripcion"));
                                //horario.put("imagenAlmuerzo", dieta.getParseFile("imagen"));
                                horario.saveInBackground();
                            }else {

                            }


                        }
                    });

                }

            }
        });

        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Dieta");
        query3.whereEqualTo("risk", riskLevel);
        query3.whereEqualTo("tipo", "cena");
        query3.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> dietas3, ParseException e) {
                if (dietas3 != null) {

                    Log.d("Dietas", "Retrieved " + dietas3.size() + " dietas");

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("HorarioDieta");
                    query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
                    query.whereEqualTo("estado", true);

                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(final ParseObject horario, ParseException e) {
                            if (horario!=null) {
                                Random ram = new Random();
                                int i = ram.nextInt(dietas3.size());
                                ParseObject dieta = dietas3.get(i);
                                horario.put("cena", dieta.getString("nombre"));
                                horario.put("descripcionCena", dieta.getString("descripcion"));
                                //horario.put("imagenCena", dieta.getParseFile("imagen"));
                                horario.saveInBackground();
                            }else {

                            }


                        }
                    });

                }

            }
        });

    }

    private void recomendacionEjercicioUser(){
        ParseObject ejercicioFinal = new ParseObject("HorarioEjercicio");
        ejercicioFinal.put("estado", true);
        ejercicioFinal.put("userId", ParseUser.getCurrentUser().getObjectId());
        ejercicioFinal.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ejercicio");
        query.whereEqualTo("risk", riskLevel);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> ejercicios, ParseException e) {
                if (ejercicios != null) {

                    Log.d("Ejercicios", "Retrieved " + ejercicios.size() + " ejercicios");

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("HorarioEjercicio");
                    query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
                    query.whereEqualTo("estado", true);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(final ParseObject horario, ParseException e) {
                            if (horario!=null) {
                                Random ram1 = new Random();
                                int i1 = ram1.nextInt(ejercicios.size());
                                int i2 = ram1.nextInt(ejercicios.size());
                                int i3 = ram1.nextInt(ejercicios.size());
                                ParseObject ejercicio1 = ejercicios.get(i1);
                                horario.put("nombreE1", ejercicio1.getString("nombre"));
                                horario.put("risk", ejercicio1.getInt("risk"));
                                horario.put("descripcionE1", ejercicio1.getString("descripcion"));
                                //horario.put("imagenE1", ejercicio1.getParseFile("imagen"));
                                horario.put("duracionE1", ejercicio1.getInt("duracion"));
                                horario.put("repeticionE1", ejercicio1.getInt("repeticion"));

                                ParseObject ejercicio2 = ejercicios.get(i2);
                                horario.put("nombreE2", ejercicio2.getString("nombre"));
                                horario.put("descripcionE2", ejercicio2.getString("descripcion"));
                                //horario.put("imagenE2", ejercicio2.getParseFile("imagen"));
                                horario.put("duracionE2", ejercicio1.getInt("duracion"));
                                horario.put("repeticionE2", ejercicio1.getInt("repeticion"));

                                ParseObject ejercicio3 = ejercicios.get(i3);
                                horario.put("nombreE3", ejercicio3.getString("nombre"));
                                horario.put("descripcionE3", ejercicio3.getString("descripcion"));
                                //horario.put("imagenE3", ejercicio3.getParseFile("imagen"));
                                horario.put("duracionE3", ejercicio1.getInt("duracion"));
                                horario.put("repeticionE3", ejercicio1.getInt("repeticion"));
                                horario.saveInBackground();
                            }else {

                            }


                        }
                    });

                }

            }
        });


    }

    public class foto extends  AsyncTask<Void, Void, String>{


        @Override
        protected String doInBackground(Void... voids) {
            recomendacionDietaUser();
            recomendacionEjercicioUser();
            agregarFotoPerfil();


            return "Termino" ;
        }
        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
        }
    }
}
