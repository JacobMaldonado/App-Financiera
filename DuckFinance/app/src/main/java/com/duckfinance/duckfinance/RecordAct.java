package com.duckfinance.duckfinance;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.duckfinance.duckfinance.adapter.TransactionAdapter;
import com.duckfinance.duckfinance.objects.Transaction;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecordAct extends AppCompatActivity {

    private List<Transaction> transactions;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_act);
        //inicialize recycler view
        recyclerView = (RecyclerView) findViewById(R.id.record_recyclerview);
        //create the list for our recycler view
        transactions = new ArrayList<>();
        //instanciate the adapter of transactions
        adapter = new TransactionAdapter(transactions);
        //set the layout manager
        layoutManager = new LinearLayoutManager(getApplicationContext());
        //set recycler view configuration
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //dummieData();
        setUpDatabaseListener();
    }

    private void dummieData() {

        Transaction transaction;
        transaction = new Transaction(
                getString(R.string.spending_category_feeding),
                "28 agosto 2018",
                30.5);
        transactions.add(transaction);

        transaction = new Transaction(
                getString(R.string.spending_category_entertainment),
                "28 agosto 2018",
                30.5);
        transactions.add(transaction);

        transaction = new Transaction(
                getString(R.string.spending_category_health_and_hygiene),
                "28 agosto 2018",
                30.5);
        transactions.add(transaction);

        transaction = new Transaction(
                getString(R.string.spending_category_health_and_hygiene),
                "28 agosto 2018",
                30.5);
        transactions.add(transaction);
        transaction = new Transaction(
                getString(R.string.spending_category_health_and_hygiene),
                "28 agosto 2018",
                30.5);
        transactions.add(transaction);

        transaction = new Transaction(
                getString(R.string.spending_category_health_and_hygiene),
                "28 agosto 2018",
                30.5);
        transactions.add(transaction);

        transaction = new Transaction(
                getString(R.string.spending_category_health_and_hygiene),
                "28 agosto 2018",
                30.5);
        transactions.add(transaction);

        adapter.notifyDataSetChanged();
    }

    private void setUpDatabaseListener() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        //TODO(5): verify path
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
                transactions.clear();

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
                        Log.e("Record Act", "Encontre " + tr.getCategory());
                        transactions.add(tr);
                    }

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
