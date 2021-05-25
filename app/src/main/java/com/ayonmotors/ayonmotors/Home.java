package com.ayonmotors.ayonmotors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements onResponse {
    TextInputEditText searchbike;
    MaterialButton search;
    TextView buy, sell, history, transaction, bikesinstock;
    Functions functions;
    DatabaseReference reference;
    ArrayList<bikemodel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchbike = findViewById(R.id.search);
        search = findViewById(R.id.searchbutton);
        buy = findViewById(R.id.buynewbike);
        sell = findViewById(R.id.sellnewbike);
        history = findViewById(R.id.buyhistory);
        transaction = findViewById(R.id.totaltransectiontoday);
        bikesinstock = findViewById(R.id.bikesinstock);
        reference = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();

        functions = new Functions();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bikename = searchbike.getText().toString();
                if (TextUtils.isEmpty(bikename)) {
                    searchbike.setError("chassis no is needed!");
                    searchbike.requestFocus();
                } else {
                    functions.showDialogue(Home.this);
                    list.clear();

                    reference.child("chassis").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapsho) {


                            if (snapsho.child("buy").hasChild(bikename)) {
                                String value = snapsho.child("buy").child(bikename).getValue(String.class);
                                String day = value.split("/")[0];
                                String month = value.split("/")[1];
                                String year = value.split("/")[2];


                                reference.child("history").child(year).child(month).child(day).child("buy").child(bikename).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Log.e("buy", snapshot.getValue().toString());
                                        onresponse(snapshot.getValue(bikemodel.class), "buy");

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                            if (snapsho.child("sell").hasChild(bikename)) {
                                String value = snapsho.child("sell").child(bikename).getValue(String.class);
                                String day = value.split("/")[0];
                                String month = value.split("/")[1];
                                String year = value.split("/")[2];

                                reference.child("history").child(year).child(month).child(day).child("sell").child(bikename).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Log.e("sell", snapshot.getValue().toString());
                                        onresponse(snapshot.getValue(bikemodel.class), "sell");

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    functions.dismissdialogue();
                }


            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, BuyOrSellNewBike.class);
                i.putExtra("data", "buy");
                startActivity(i);
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, BuyOrSellNewBike.class);
                i.putExtra("data", "sell");
                startActivity(i);
            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, BuyOrSellHistory.class);
                startActivity(i);
            }
        });


        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, TransactionHistory.class);
                i.putExtra("time", "1");
                startActivity(i);
            }
        });


        bikesinstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, BIkesInStock.class));
            }
        });

    }

    @Override
    public void onresponse(bikemodel bikemodel, String view) {
        list.add(bikemodel);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("list", String.valueOf(list.size()));

                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                View vi = getLayoutInflater().inflate(R.layout.dialogueview, null, false);
                RecyclerView rv = vi.findViewById(R.id.recyclerview2);
                HistoryAdapter adapter = new HistoryAdapter(list, Home.this);
                rv.setLayoutManager(new LinearLayoutManager(Home.this));
                rv.setAdapter(adapter);

                builder.setView(vi);
                dialog = builder.create();
                dialog.show();


            }
        });

    }


}