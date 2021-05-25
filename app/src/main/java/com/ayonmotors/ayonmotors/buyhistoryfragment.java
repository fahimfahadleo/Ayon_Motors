package com.ayonmotors.ayonmotors;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class buyhistoryfragment extends Fragment {

    Context context;
    ArrayList<bikemodel> model;
    public buyhistoryfragment(Context context, ArrayList<bikemodel> model){
        this.context = context;
        this.model = model;

        Log.e("model",model.get(0).toString());

    }
    RecyclerView view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.framgnet_buy_history,container,false);
         view = vi.findViewById(R.id.buyhistoryrecyclerview);
        view.setLayoutManager(new LinearLayoutManager(context));



        HistoryAdapter adapter = new HistoryAdapter(model,context);
        view.setAdapter(adapter);
        return vi;
    }
}
