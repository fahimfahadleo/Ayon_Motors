package com.ayonmotors.ayonmotors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    ArrayList<transactionmodel> list;
    Context context;

    public TransactionAdapter(ArrayList<transactionmodel> list, Context context) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.transectionitem, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(TransactionAdapter.ViewHolder holder, int position) {
        final transactionmodel bikemodel = list.get(position);

        holder.nameview.setText(bikemodel.getCustomarname());
        holder.sellorbuystatus.setText(bikemodel.getStatus());
        holder.chassisnumber.setText(bikemodel.getChassis());
        holder.bikename.setText(bikemodel.getBikename());
        holder.price.setText(bikemodel.getPrice());
        holder.date.setText(bikemodel.getDate());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameview;
        public TextView sellorbuystatus;
        public TextView bikename;
        public TextView chassisnumber;
        public TextView price;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            this.nameview = itemView.findViewById(R.id.name);
            this.sellorbuystatus = itemView.findViewById(R.id.sellorbuystatus);
            this.bikename = itemView.findViewById(R.id.bikename);
            this.chassisnumber = itemView.findViewById(R.id.chassisnumber);
            this.price = itemView.findViewById(R.id.price);
            this.date = itemView.findViewById(R.id.date);
        }
    }
}
