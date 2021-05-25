package com.ayonmotors.ayonmotors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BIkesInStock extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<bikemodel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_ikes_in_stock);
        recyclerView = findViewById(R.id.rcyclerview);
        reference = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();

        reference.child("stock").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    list.add(dataSnapshot.getValue(bikemodel.class));
                }
                HistoryAdapter adapter = new HistoryAdapter(list,BIkesInStock.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(BIkesInStock.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}