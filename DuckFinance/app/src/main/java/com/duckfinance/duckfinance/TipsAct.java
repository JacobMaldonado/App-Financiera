package com.duckfinance.duckfinance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.duckfinance.duckfinance.adapter.TipAdapter;
import com.duckfinance.duckfinance.adapter.TransactionAdapter;
import com.duckfinance.duckfinance.objects.Tip;
import com.duckfinance.duckfinance.objects.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TipsAct extends AppCompatActivity {

    private List<Tip> tips;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TipAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_act);

        //inicialize recycler view
        recyclerView = (RecyclerView) findViewById(R.id.tips_recyclerview);
        //create the list for our recycler view
        tips = new ArrayList<>();
        //instanciate the adapter of transactions
        adapter = new TipAdapter(tips);
        //set the layout manager
        layoutManager = new LinearLayoutManager(getApplicationContext());
        //set recycler view configuration
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        dummieData();
    }

    private void dummieData(){

        Tip tip;
        for (int i = 0; i < 10; i++) {
            tip = new Tip("Ayuda Financiera",
                    "Descripcion del consejo de valor que se aportara a la persona",
                    R.drawable.ic_launcher_background);
            tips.add(tip);
        }
        adapter.notifyDataSetChanged();
    }
}
