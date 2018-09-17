package com.duckfinance.duckfinance.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duckfinance.duckfinance.R;
import com.duckfinance.duckfinance.objects.Tip;
import com.duckfinance.duckfinance.objects.Transaction;

import java.util.List;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.MyViewHolder>{

    private List<Tip> tips;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, description;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.tip_image);
            title = itemView.findViewById(R.id.tip_title);
            description = itemView.findViewById(R.id.tip_description);
        }
    }

    public TipAdapter(List<Tip> tips) {
        this.tips = tips;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tips, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tip currentTip = tips.get(position);

        holder.description.setText(currentTip.getDescription());

        holder.title.setText(currentTip.getTitle());

        holder.image.setImageResource(currentTip.getImageID());
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }
}
