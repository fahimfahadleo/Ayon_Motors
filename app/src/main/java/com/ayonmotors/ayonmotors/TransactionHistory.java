package com.ayonmotors.ayonmotors;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {
    String time;
    Functions functions;
    DatabaseReference reference;
    ArrayList<transactionmodel> list;
    RecyclerView recyclerView;
    TextInputEditText yeartext, monthtext, daytext;
    MaterialButton search;
    TextView total;
    int totalstr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        functions = new Functions();
        time = getIntent().getStringExtra("time");
        //functions.showDialogue(this);
        reference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.transection);
        yeartext = findViewById(R.id.year);
        monthtext = findViewById(R.id.month);
        daytext = findViewById(R.id.day);
        search = findViewById(R.id.searchbutton);
        total = findViewById(R.id.total);


        list = new ArrayList<>();
//        Date d = new Date();
//        String day = android.text.format.DateFormat.format("dd", d).toString(); // 20
//        String monthNumber = android.text.format.DateFormat.format("MM", d).toString(); // 06
//        String year = android.text.format.DateFormat.format("yyyy", d).toString(); // 2013


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yearstr = yeartext.getText().toString();
                String monthstr = monthtext.getText().toString();
                String daystr = daytext.getText().toString();

                if (TextUtils.isEmpty(yearstr)) {
                    yeartext.setError("Field Can Not Be Empty!");
                    yeartext.requestFocus();
                } else {
                    if (TextUtils.isEmpty(monthstr)) {
                        if (TextUtils.isEmpty(daystr)) {

                            if (recyclerView.getAdapter() != null) {
                                recyclerView.setAdapter(null);
                                list.clear();
                                totalstr = 0;
                            }

                            reference.child("transaction").child(yearstr).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot d : snapshot.getChildren()) {
                                        for (DataSnapshot d2 : d.getChildren()) {
                                            for (DataSnapshot d4 : d2.child("sell").getChildren()) {

                                                transactionmodel transactionmodel = d4.getValue(com.ayonmotors.ayonmotors.transactionmodel.class);
                                                totalstr = totalstr+Integer.parseInt(transactionmodel.getPrice());
                                                list.add(transactionmodel);
                                            }
                                            for (DataSnapshot d4 : d2.child("buy").getChildren()) {
                                                transactionmodel transactionmodel = d4.getValue(com.ayonmotors.ayonmotors.transactionmodel.class);
                                                totalstr = totalstr-Integer.parseInt(transactionmodel.getPrice());
                                                list.add(transactionmodel);

                                            }
                                        }
                                    }

                                    TransactionAdapter adapter = new TransactionAdapter(list, TransactionHistory.this);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(TransactionHistory.this));
                                    recyclerView.setAdapter(adapter);
                                    total.setText("Total: "+totalstr);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            monthtext.setError("Field Can Not Be Empty!");
                            monthtext.requestFocus();
                        }
                    } else {

                        if(monthstr.length()==1){
                            monthstr = "0"+monthstr;
                        }

                        if (TextUtils.isEmpty(daystr)) {

                            if (recyclerView.getAdapter() != null) {
                                recyclerView.setAdapter(null);
                                list.clear();
                                totalstr = 0;
                            }

                            reference.child("transaction").child(yearstr).child(monthstr).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot d : snapshot.getChildren()) {
                                        for (DataSnapshot d4 : d.child("sell").getChildren()) {
                                            transactionmodel transactionmodel = d4.getValue(com.ayonmotors.ayonmotors.transactionmodel.class);
                                            totalstr = totalstr+Integer.parseInt(transactionmodel.getPrice());
                                            list.add(transactionmodel);
                                        }
                                        for (DataSnapshot d4 : d.child("buy").getChildren()) {
                                            transactionmodel transactionmodel = d4.getValue(com.ayonmotors.ayonmotors.transactionmodel.class);
                                            totalstr = totalstr-Integer.parseInt(transactionmodel.getPrice());
                                            list.add(transactionmodel);
                                        }


                                    }


                                    TransactionAdapter adapter = new TransactionAdapter(list, TransactionHistory.this);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(TransactionHistory.this));
                                    recyclerView.setAdapter(adapter);
                                    total.setText("Total: "+totalstr);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {

                            if(daystr.length()==1){
                                daystr = "0"+daystr;
                            }

                            if (recyclerView.getAdapter() != null) {
                                recyclerView.setAdapter(null);
                                list.clear();
                                totalstr = 0;
                            }

                            reference.child("transaction").child(yearstr).child(monthstr).child(daystr).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    for (DataSnapshot d4 : snapshot.child("sell").getChildren()) {
                                        transactionmodel transactionmodel = d4.getValue(com.ayonmotors.ayonmotors.transactionmodel.class);
                                        totalstr = totalstr+Integer.parseInt(transactionmodel.getPrice());
                                        list.add(transactionmodel);
                                    }                                    for (DataSnapshot d4 : snapshot.child("buy").getChildren()) {
                                        transactionmodel transactionmodel = d4.getValue(com.ayonmotors.ayonmotors.transactionmodel.class);
                                        totalstr = totalstr-Integer.parseInt(transactionmodel.getPrice());
                                        list.add(transactionmodel);
                                    }



                                    TransactionAdapter adapter = new TransactionAdapter(list, TransactionHistory.this);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(TransactionHistory.this));
                                    recyclerView.setAdapter(adapter);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }
            }
        });
    }
}