package com.tesis2.lifestyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentoEvaluar extends Fragment implements View.OnClickListener {
    Button evaluar;

    public FragmentoEvaluar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_evaluar, container, false);

        evaluar = (Button) view.findViewById(R.id.butEvaluar);

        evaluar.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(), LogroEvaluacionActivity.class));
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().finish();
    }
}
