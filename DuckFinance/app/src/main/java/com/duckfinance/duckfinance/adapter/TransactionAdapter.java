package com.duckfinance.duckfinance.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.duckfinance.duckfinance.R;
import com.duckfinance.duckfinance.objects.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder>{

    private List<Transaction> transactions;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView fecha, gasto, categoria;
        public ImageView colorFlecha;
        public View lineaSuperior;

        public MyViewHolder(View itemView) {
            super(itemView);
            fecha = (TextView) itemView.findViewById(R.id.transaction_date);
            gasto = (TextView) itemView.findViewById(R.id.transaction_spending);
            categoria = (TextView) itemView.findViewById(R.id.transaction_description);
            colorFlecha = (ImageView) itemView.findViewById(R.id.transaction_image);
            lineaSuperior = (View) itemView.findViewById(R.id.transaction_topline);
        }
    }

    public TransactionAdapter(List<Transaction> transactions){
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Transaction currentTransaction = transactions.get(position);
        holder.fecha.setText(currentTransaction.getDate());
        holder.categoria.setText(currentTransaction.getCategory());
        holder.gasto.setText("$" + currentTransaction.getAmount());
        holder.colorFlecha.setImageResource(currentTransaction.getImageIdColor());
        if(position == 0) {
            holder.lineaSuperior.setVisibility(View.INVISIBLE);
            //TODO: set color for image
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

}

