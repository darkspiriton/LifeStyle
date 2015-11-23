package com.tesis2.lifestyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LogroRiskActivity extends AppCompatActivity implements View.OnClickListener {
    private Button butRisk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_logro);
        butRisk = (Button) findViewById(R.id.butRisk);

        butRisk.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, MenuActivity.class));
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
