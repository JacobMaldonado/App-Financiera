package com.duckfinance.duckfinance;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.duckfinance.duckfinance.objects.Transaction;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BalanceAct extends AppCompatActivity {


    PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_act);

        chart = (PieChart) findViewById(R.id.balance_piechart);
        chart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);
        chart.setUsePercentValues(true);
        //chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);


        setUpDatabaseListener();

    }
/*
    @NonNull
    private Color hex2Rgb(String colorStr) {
        return new Color.rgb(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }*/

    private void setUpDatabaseListener() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //TODO: make point to register user
                String user = "jacob";
                Iterator<DataSnapshot> i = dataSnapshot.child(user).child("gastos").getChildren().iterator();
                Iterator<DataSnapshot> cat;
                String fecha, categoria, cantidad;
                Transaction tr;
                List<Transaction> transactions = new ArrayList<>();

                while (i.hasNext()) {
                    DataSnapshot data = i.next();
                    categoria = data.getKey();
                    cat = data.getChildren().iterator();
                    while (cat.hasNext()) {
                        DataSnapshot register = cat.next();
                        cantidad = register.child("cantidad").getValue().toString();
                        fecha = register.child("fecha").getValue().toString();
                        //add new transaction
                        tr = new Transaction(categoria, fecha, Double.parseDouble(cantidad));
                        transactions.add(tr);
                    }

                }

                chargeDataToChart(transactions);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private void chargeDataToChart(List<Transaction> transactions){
        //TODO: make it async
        List<PieEntry> pieEntries = new ArrayList<>();
        float[] amounts = new float[8];
        float total = 0;

        //group every amount of each category
        for(Transaction tr : transactions){
            amounts[tr.getCategoryAsNumber()] += tr.getAmount();
            total += tr.getAmount();
        }

        //add pieEntry for each category
        for (int i = 0; i < amounts.length; i++){
            pieEntries.add(new PieEntry((amounts[i] / total)*100,
                    Transaction.getCategoryNameFromNumber(i)));
        }

        //creating dataset
        PieDataSet dataSet = new PieDataSet(pieEntries, "Label");
        //styling
        dataSet.setColors(
                getResources().getColor(R.color.category_feeding),
                getResources().getColor(R.color.category_bills_and_payments),
                getResources().getColor(R.color.category_home),
                getResources().getColor(R.color.category_clothes),
                getResources().getColor(R.color.category_entertainment),
                getResources().getColor(R.color.category_transport),
                getResources().getColor(R.color.category_healt_and_hygiene),
                getResources().getColor(R.color.category_others)
                );
        dataSet.setValueTextColor(Color.WHITE);
        //space
        dataSet.setSliceSpace(3f);
        //black lines
        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);

        //set data to chart
        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);
        chart.invalidate(); //refreshing data

    }
}
