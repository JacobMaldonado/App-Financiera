package com.duckfinance.duckfinance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.duckfinance.duckfinance.adapter.SpendingsAdapter;
import com.duckfinance.duckfinance.adapter.TipAdapter;
import com.duckfinance.duckfinance.objects.SpendingCategory;
import com.duckfinance.duckfinance.objects.Tip;
import com.duckfinance.duckfinance.objects.Transaction;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NewTransactionAct extends AppCompatActivity implements SpendingsAdapter.OnClickListener {

    EditText amount;

    private List<SpendingCategory> categories;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SpendingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtransaction_activity);

        amount = (EditText) findViewById(R.id.newtransaction_edittext);

        //inicialize recycler view
        recyclerView = (RecyclerView) findViewById(R.id.newtransaction_recyclerview);
        //create the list for our recycler view
        categories = new ArrayList<>();
        //instanciate the adapter of transactions
        adapter = new SpendingsAdapter(categories,this );
        //set the layout manager
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);

        //set recycler view configuration
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        loadCategories();
    }

    private void loadCategories() {

        SpendingCategory spending;

        //Alimentacion
        spending = new SpendingCategory("Alimentacion", R.drawable.re_3);
        categories.add(spending);
        //Cuentas y Pagos
        spending = new SpendingCategory(getString(R.string.spending_category_bills_and_payments), R.drawable.re_4);
        categories.add(spending);
        //Vivienda
        spending = new SpendingCategory("Casa", R.drawable.re_1);
        categories.add(spending);
        //Salud e higiene
        spending = new SpendingCategory(getString(R.string.spending_category_health_and_hygiene), R.drawable.re_5);
        categories.add(spending);
        //Ropa
        spending = new SpendingCategory(getString(R.string.spending_category_clothes), R.drawable.re_2);
        categories.add(spending);

        //Diversion
        spending = new SpendingCategory(getString(R.string.spending_category_entertainment), R.drawable.re_6);
        categories.add(spending);
        //transporte
        spending = new SpendingCategory(getString(R.string.spending_category_transport), R.drawable.re_0);
        categories.add(spending);

        //Otros Gastos
        spending = new SpendingCategory(getString(R.string.spending_category_others), R.drawable.re_7);
        categories.add(spending);

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v, int position) {
        //TODO: save amount in database
        SpendingCategory sp = categories.get(position);
        String category = sp.getName();
        String text = amount.getText().toString().trim();
        if( !text.isEmpty() ) {
            sendToDatabase(category, Double.parseDouble(text));
            Intent i = new Intent(this, InicioAct.class);
            startActivity(i);
            Toast.makeText(this,"Se registro tu gasto", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Escribe una cantidad", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendToDatabase(String category, double amount){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        String user = "jacob";
        //get the reference
        DatabaseReference gastosRef = myRef.child(user).child("gastos").child(category);
        String key = gastosRef.push().getKey();
        //create json object
        HashMap<String, Object> data = new HashMap<>();
        data.put("cantidad", new Double(amount));
        //TODO: Format date
        data.put("fecha", new Date().toString());
        //set data
        gastosRef.child(key).setValue(data);

    }
}
