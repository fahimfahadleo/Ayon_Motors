package com.ayonmotors.ayonmotors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class BuyOrSellNewBike extends AppCompatActivity {

    TextView title;
    TextInputEditText customarname,phonenumber,address,date,bikename,regnumber,chassisnumber,enginenumber,color,manufactureyear,manufacturecountry,cc,brand,price,description;
    String titletext = null;
    TextView setengine,setregistration,setchessis;
    MaterialButton submit;
    DatabaseReference reference;
    Functions functions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_sell_new_bike);
        title = findViewById(R.id.titletext);
        titletext = getIntent().getStringExtra("data");
        title.setText(titletext);
        reference = FirebaseDatabase.getInstance().getReference();
        customarname = findViewById(R.id.customarname);
        phonenumber = findViewById(R.id.phonenumber);
        address = findViewById(R.id.address);
        date = findViewById(R.id.date);
        bikename = findViewById(R.id.motorcyclename);
        regnumber = findViewById(R.id.registrationnumber);
        chassisnumber = findViewById(R.id.chassisnumber);
        enginenumber = findViewById(R.id.enginenumber);
        color = findViewById(R.id.motorcyclecolor);
        manufactureyear = findViewById(R.id.manufactureyear);
        manufacturecountry = findViewById(R.id.manufacturecountry);
        cc = findViewById(R.id.cc);
        brand = findViewById(R.id.brand);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        submit = findViewById(R.id.submitbutton);
        setchessis = findViewById(R.id.setchassis);
        setengine = findViewById(R.id.setengine);
        setregistration = findViewById(R.id.setregistration);
        functions = new Functions();

        if(titletext.equals("buy")){
            setchessis.setVisibility(View.GONE);
            setengine.setVisibility(View.GONE);
            setregistration.setVisibility(View.GONE);
        }

        setchessis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String chassisnumberstr = chassisnumber.getText().toString();


                if(TextUtils.isEmpty(chassisnumberstr)){
                    chassisnumber.setError("Field Must Be Filled!");
                    chassisnumber.requestFocus();
                }else {
                    functions.showDialogue(BuyOrSellNewBike.this);

                    reference.child("chassis").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapsho) {
                            for(DataSnapshot snapshot:snapsho.getChildren()){
                                if(snapshot.hasChild(chassisnumberstr)){
                                    String value = snapshot.child(chassisnumberstr).getValue(String.class);
                                    String day = value.split("/")[0];
                                    String month = value.split("/")[1];
                                    String year = value.split("/")[2];

                                    reference.child("history").child(year).child(month).child(day).child("buy").child(chassisnumberstr).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            bikemodel bikemodel = snapshot.getValue(com.ayonmotors.ayonmotors.bikemodel.class);

                                            bikename.setText(bikemodel.getBikename());
                                            regnumber.setText(bikemodel.getRegistrationnumber());
                                            chassisnumber.setText(bikemodel.getChassisnumber());
                                            enginenumber.setText(bikemodel.getEnginenumber());
                                            color.setText(bikemodel.getColor());
                                            manufactureyear.setText(bikemodel.getManufactureyear());
                                            manufacturecountry.setText(bikemodel.getManufacturecountry());
                                            cc.setText(bikemodel.getCc());
                                            brand.setText(bikemodel.getBrand());
                                            description.setText(bikemodel.getDescription());

                                            functions.dismissdialogue();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        setengine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String chassisnumberstr = enginenumber.getText().toString();


                if(TextUtils.isEmpty(chassisnumberstr)){
                    enginenumber.setError("Field Must Be Filled!");
                    enginenumber.requestFocus();
                }else {
                    functions.showDialogue(BuyOrSellNewBike.this);

                    reference.child("engine").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapsho) {
                            for(DataSnapshot snapshot:snapsho.getChildren()){
                                if(snapshot.hasChild(chassisnumberstr)){
                                    String value = snapshot.child(chassisnumberstr).getValue(String.class);
                                    String day = value.split("/")[0];
                                    String month = value.split("/")[1];
                                    String year = value.split("/")[2];

                                    reference.child("history").child(year).child(month).child(day).child("buy").child(chassisnumberstr).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            bikemodel bikemodel = snapshot.getValue(com.ayonmotors.ayonmotors.bikemodel.class);

                                            bikename.setText(bikemodel.getBikename());
                                            regnumber.setText(bikemodel.getRegistrationnumber());
                                            chassisnumber.setText(bikemodel.getChassisnumber());
                                            enginenumber.setText(bikemodel.getEnginenumber());
                                            color.setText(bikemodel.getColor());
                                            manufactureyear.setText(bikemodel.getManufactureyear());
                                            manufacturecountry.setText(bikemodel.getManufacturecountry());
                                            cc.setText(bikemodel.getCc());
                                            brand.setText(bikemodel.getBrand());
                                            description.setText(bikemodel.getDescription());

                                            functions.dismissdialogue();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        setregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String chassisnumberstr = regnumber.getText().toString();
                Log.e("regnumber",chassisnumberstr);


                if(TextUtils.isEmpty(chassisnumberstr)){
                    regnumber.setError("Field Must Be Filled!");
                    regnumber.requestFocus();
                }else {
                    functions.showDialogue(BuyOrSellNewBike.this);
                    reference.child("registration").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapsho) {
                            for(DataSnapshot snapshot:snapsho.getChildren()){
                                if(snapshot.hasChild(chassisnumberstr)){
                                    String value = snapshot.child(chassisnumberstr).getValue(String.class);
                                    String day = value.split("/")[0];
                                    String month = value.split("/")[1];
                                    String year = value.split("/")[2];



                                    reference.child("history").child(year).child(month).child(day).child("buy").child(chassisnumberstr).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshott) {
                                            bikemodel bikemodel = snapshott.getValue(com.ayonmotors.ayonmotors.bikemodel.class);


                                            bikename.setText(bikemodel.getBikename());
                                            regnumber.setText(bikemodel.getRegistrationnumber());
                                            chassisnumber.setText(bikemodel.getChassisnumber());
                                            enginenumber.setText(bikemodel.getEnginenumber());
                                            color.setText(bikemodel.getColor());
                                            manufactureyear.setText(bikemodel.getManufactureyear());
                                            manufacturecountry.setText(bikemodel.getManufacturecountry());
                                            cc.setText(bikemodel.getCc());
                                            brand.setText(bikemodel.getBrand());
                                            description.setText(bikemodel.getDescription());

                                            functions.dismissdialogue();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });




        String s =android.text.format.DateFormat.format("dd/MM/yyyy", new java.util.Date()).toString();

        date.setText(s);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String customarnamestr = customarname.getText().toString();
                String phonenumberstr = phonenumber.getText().toString();
                String addressstr = address.getText().toString();
                String datestr = date.getText().toString();
                String bikenamestr = bikename.getText().toString();
                String registernostr = regnumber.getText().toString();
                String chassisnumberstr = chassisnumber.getText().toString();
                String enginenumberstr = enginenumber.getText().toString();
                String colorstr = color.getText().toString();
                String manufactureyearstr = manufactureyear.getText().toString();
                String manufacturecountyestr = manufacturecountry.getText().toString();
                String ccstr = cc.getText().toString();
                String brandstr = brand.getText().toString();
                String pricestr = price.getText().toString();
                String descriptionstr = description.getText().toString();

                if(TextUtils.isEmpty(customarnamestr)){
                    customarname.setError("Customer Name Must Be Filled!");
                    customarname.requestFocus();
                }else if(TextUtils.isEmpty(phonenumberstr)){
                    phonenumber.setError("Phone Number Must Be Filled!");
                    phonenumber.requestFocus();
                }else if(TextUtils.isEmpty(addressstr)){
                    address.setError("Address Must Be Filled!");
                    address.requestFocus();
                }else if(TextUtils.isEmpty(datestr)){
                    date.setError("Date Must Be Filled!");
                    date.requestFocus();
                }else if(TextUtils.isEmpty(bikenamestr)){
                    bikename.setError("Bike Name Must Be Filled!");
                    bikename.requestFocus();
                }else if(TextUtils.isEmpty(chassisnumberstr)){
                    chassisnumber.setError("Chassis Number Must Be Filled!");
                    chassisnumber.requestFocus();
                }else if(TextUtils.isEmpty(enginenumberstr)){
                    enginenumber.setError("Engine Number Must Be Filled!");
                    enginenumber.requestFocus();
                }else if(TextUtils.isEmpty(colorstr)){
                    color.setError("Bike Color Must Be Filled!");
                    color.requestFocus();
                }else if(TextUtils.isEmpty(manufactureyearstr)){
                    manufactureyear.setError("Manufacture Year Must Be Filled!");
                    manufactureyear.requestFocus();
                }else if(TextUtils.isEmpty(manufacturecountyestr)){
                    manufacturecountry.setError("Manufacture Country Must Be Filled!");
                    manufacturecountry.requestFocus();
                }else if(TextUtils.isEmpty(ccstr)){
                    cc.setError("Bike CC Must Be Filled!");
                    cc.requestFocus();
                }else if(TextUtils.isEmpty(brandstr)){
                    brand.setError("Bike Brand Name Must Be Filled!");
                    brand.requestFocus();
                }else if(TextUtils.isEmpty(pricestr)){
                    price.setError("Price Must Be Filled!");
                    price.requestFocus();
                }else if(TextUtils.isEmpty(descriptionstr)){
                    description.setError("Description Must Be Filled!");
                    description.requestFocus();
                }else {
                    functions.showDialogue(BuyOrSellNewBike.this);

                    bikemodel model = new bikemodel();
                    model.setCustomarname(customarnamestr);
                    model.setPhonenumber(phonenumberstr);
                    model.setAddress(addressstr);
                    model.setDate(datestr);
                    model.setBikename(bikenamestr);
                    if(TextUtils.isEmpty(registernostr)){
                        registernostr = " ";
                        Toast.makeText(BuyOrSellNewBike.this, "This Bike Can Not Be Searched by Register Number!", Toast.LENGTH_SHORT).show();
                    }
                    model.setRegistrationnumber(registernostr);
                    model.setChassisnumber(chassisnumberstr);
                    model.setEnginenumber(enginenumberstr);
                    model.setColor(colorstr);
                    model.setManufactureyear(manufactureyearstr);
                    model.setManufacturecountry(manufacturecountyestr);
                    model.setCc(ccstr);
                    model.setBrand(brandstr);
                    model.setPrice(pricestr);
                    model.setDescription(descriptionstr);

                    Date d = new Date();
                    String day          = android.text.format.DateFormat.format("dd",   d).toString(); // 20
                    String monthNumber  = android.text.format.DateFormat.format("MM",   d).toString(); // 06
                    String year         = android.text.format.DateFormat.format("yyyy", d).toString(); // 2013
                    HashMap<String,String> tempmap = new HashMap<>();

                    tempmap.put("customarname",model.getCustomarname());
                    tempmap.put("bikename",model.getBikename());
                    tempmap.put("chassis",model.getChassisnumber());
                    tempmap.put("price",model.getPrice());
                    tempmap.put("date",day+"/"+monthNumber+"/"+year);


                    if(titletext.equals("buy")){
                        reference.child("history").child(year).child(monthNumber).child(day).child("buy").child(model.getChassisnumber()).setValue(model);
                        reference.child("chassis").child("buy").child(model.getChassisnumber()).setValue(model.getDate());
                        reference.child("engine").child("buy").child(model.getEnginenumber()).setValue(model.getDate()+" + "+model.getChassisnumber());
                        if(!TextUtils.isEmpty(registernostr)){
                            reference.child("registration").child("buy").child(model.getRegistrationnumber()).setValue(model.getDate()+" + "+model.getChassisnumber());
                        }
                        reference.child("stock").child(model.getChassisnumber()).setValue(model);
                        tempmap.put("status","buy");
                        reference.child("transaction").child(year).child(monthNumber).child(day).child("buy").child(model.getChassisnumber()).setValue(tempmap);
                    }else {
                        reference.child("history").child(year).child(monthNumber).child(day).child("sell").child(model.getChassisnumber()).setValue(model);
                        reference.child("stock").child(model.getChassisnumber()).removeValue();
                        reference.child("chassis").child("sell").child(model.getChassisnumber()).setValue(model.getDate());
                        reference.child("engine").child("sell").child(model.getEnginenumber()).setValue(model.getDate());
                        if(!TextUtils.isEmpty(registernostr)){
                            reference.child("registration").child("sell").child(model.getRegistrationnumber()).setValue(model.getDate());
                        }
                        tempmap.put("status","sell");
                        reference.child("transaction").child(year).child(monthNumber).child(day).child("sell").child(model.getChassisnumber()).setValue(tempmap);
                    }

                    customarname.setText(null);
                    phonenumber.setText(null);
                    address.setText(null);
                    bikename.setText(null);
                    regnumber.setText(null);
                    chassisnumber.setText(null);
                    enginenumber.setText(null);
                    color.setText(null);
                    manufactureyear.setText(null);
                    manufacturecountry.setText(null);
                    cc.setText(null);
                    brand.setText(null);
                    description.setText(null);
                    price.setText(null);


                    functions.dismissdialogue();
                }
            }
        });



    }
}