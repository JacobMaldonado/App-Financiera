package com.duckfinance.duckfinance.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duckfinance.duckfinance.R;
import com.duckfinance.duckfinance.objects.SpendingCategory;

import java.util.List;

public class SpendingsAdapter extends RecyclerView.Adapter<SpendingsAdapter.MyViewHolder>{

    private OnClickListener clickListener;
    private List<SpendingCategory> categories;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.spending_category_image);
            itemView.setOnClickListener(this);
            //TODO(3.1): add listener

        }


        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }

    public SpendingsAdapter(List<SpendingCategory> categories, OnClickListener listener) {
        clickListener = listener;
        this.categories = categories;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spending_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SpendingCategory currentCategory = categories.get(position);

        holder.imageView.setImageResource(currentCategory.getIdImage());
        //TODO(4): Change images on this buttons

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface OnClickListener{
        void onClick(View v, int position);
    }
}
