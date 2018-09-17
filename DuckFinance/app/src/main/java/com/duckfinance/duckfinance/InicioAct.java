package com.duckfinance.duckfinance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class InicioAct extends AppCompatActivity {

    private ImageView ibtnRecord, ibtnBalance, ibtnReview, ibtnTips;
    private Button btnRegister;
    private boolean isCreated = false;
    //TODO(5): agregar iconos de inicio


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_act);

        ibtnRecord = (ImageView) findViewById(R.id.home_ibtn_record);
        ibtnBalance = (ImageView) findViewById(R.id.home_ibtn_balance);
        ibtnReview = (ImageView) findViewById(R.id.home_ibtn_review);
        ibtnTips = (ImageView) findViewById(R.id.home_ibtn_tips);
        btnRegister = (Button) findViewById(R.id.home_ibtn_newtransaction);

        ibtnRecord.setOnClickListener(recordClicked());
        ibtnBalance.setOnClickListener(balanceClicked());
        ibtnReview.setOnClickListener(reviewClicked());
        ibtnTips.setOnClickListener(tipsClicked());
        btnRegister.setOnClickListener(newRegisterClicked());


    }


    //Click listener for ImageView called RECORD
    private View.OnClickListener recordClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioAct.this, RecordAct.class);
                startActivity(i);
            }
        };
    }

    //Click listener for ImageView called BALANCE
    private View.OnClickListener balanceClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioAct.this, BalanceAct.class);
                startActivity(i);
            }
        };
    }

    //Click listener for ImageView called REVIEW
    private View.OnClickListener reviewClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Hacer el intent
                Intent i = new Intent(InicioAct.this, AnalisisAct.class);
                startActivity(i);
            }
        };
    }

    //Click Listener for ImageView called TIPS
    private View.OnClickListener tipsClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO(2): Hacer intent
                Intent i = new Intent(InicioAct.this, TipsAct.class);
                startActivity(i);
            }
        };
    }

    //Click Listener for Button called NUEVO REGISTRO

    private View.OnClickListener newRegisterClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioAct.this, NewTransactionAct.class);
                startActivity(i);
            }
        };
    }

}
