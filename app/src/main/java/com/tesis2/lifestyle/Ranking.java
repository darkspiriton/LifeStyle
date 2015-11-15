package com.tesis2.lifestyle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class Ranking extends AppCompatActivity {
    private RankingAdapter rankingAdapter;
    private ListView ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Provee la informacion y tambien genera los views
        rankingAdapter = new RankingAdapter(this);

        //lista donde se puesta los datos
        ranking = (ListView) findViewById(R.id.lvDetalleRanking);

        //relacionar el adaptador con la lista
        ranking.setAdapter(rankingAdapter);
        rankingAdapter.loadObjects();
    }
}


