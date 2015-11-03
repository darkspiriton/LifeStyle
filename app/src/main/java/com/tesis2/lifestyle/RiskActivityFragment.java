package com.tesis2.lifestyle;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class RiskActivityFragment extends Fragment implements View.OnClickListener{

    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;
    private Button butFin;
    private RadioButton r1_1,r1_2,r1_3,r1_4,r2_1,r2_2,r4_1,r4_2,r4_3,r5_1,r5_2,r6_1,r6_2,r7_1,r7_2,r8_1,r8_2,r9_1,r9_2,r9_3;
    private static int r1=6,r3,r4=6,r5=6,r6=6,r7=6,r8=6,r9=6;
    private static String r2="";
    private static double IMC;
    private static EditText r3_1,r3_2;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */

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

                    //falta guardar el resultado parse
                    //falta obtener recomendaciones
                    //obtener nivel de riesgo
                    //mostrar resultado

                    int riesgo = r1+r3+r4+r5+r6+r7+r8+r9;
                    Context context = view.getContext();
                    CharSequence text = "Boton Finalizar Encuesta - IMC: "+ r1+r2+r3+r4+r5+r6+r7+r8+r9 + "total"+riesgo ;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    /**
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    currentUser.put("evaluation",true);

                    currentUser.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {

                            } else {

                            }
                        }
                    });
                    **/
                    Intent intent = new Intent(getActivity(),MenuActivity.class);
                    startActivity(intent);

                } catch (Exception e){
                    Context context = view.getContext();
                    CharSequence text = "Los valores de peso y talla no son numericos" ;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }

        } else {
            Context context=null;
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

}
