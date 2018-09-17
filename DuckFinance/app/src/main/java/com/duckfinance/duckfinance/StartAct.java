package com.duckfinance.duckfinance;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartAct extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_act);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(StartAct.this, InicioAct.class);
                StartAct.this.startActivity(i);
                StartAct.this.finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
