package com.ayonmotors.ayonmotors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    ArrayList<bikemodel> list;
    Context context;

    public HistoryAdapter(ArrayList<bikemodel> list, Context context) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.historyitem, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        final bikemodel bikemodel = list.get(position);
        holder.nameview.setText(bikemodel.getCustomarname());
        holder.chassisnumber.setText(bikemodel.getChassisnumber());
        holder.bikename.setText(bikemodel.getBikename());
        holder.price.setText(bikemodel.getPrice());
        holder.phone.setText(bikemodel.getPhonenumber());
        holder.address.setText(bikemodel.getAddress());
        holder.enginenumber.setText(bikemodel.getEnginenumber());
        holder.registrationnumber.setText(bikemodel.getRegistrationnumber());
        holder.color.setText(bikemodel.getColor());
        holder.description.setText(bikemodel.getDescription());
        holder.date.setText(bikemodel.getDate());
        holder.title.setText(bikemodel.getBikename());

        Log.e("adapter",bikemodel.toString());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameview;
        public TextView phone;
        public TextView address;
        public TextView bikename;
        public TextView chassisnumber;
        public TextView price;
        public TextView date;
        public TextView enginenumber;
        public TextView registrationnumber;
        public TextView description;
        public TextView color;
        public TextView title;


        public ViewHolder(View itemView) {
            super(itemView);

            this.nameview = itemView.findViewById(R.id.name);
            this.bikename = itemView.findViewById(R.id.bikename);
            this.chassisnumber = itemView.findViewById(R.id.chassisnumber);
            this.price = itemView.findViewById(R.id.price);
            this.phone = itemView.findViewById(R.id.phone);
            this.address = itemView.findViewById(R.id.address);
            this.date = itemView.findViewById(R.id.date);
            this.enginenumber = itemView.findViewById(R.id.enginenumber);
            this.registrationnumber = itemView.findViewById(R.id.registrationnumber);
            this.description = itemView.findViewById(R.id.description);
            this.color = itemView.findViewById(R.id.color);
            this.title = itemView.findViewById(R.id.title);
        }
    }
}
