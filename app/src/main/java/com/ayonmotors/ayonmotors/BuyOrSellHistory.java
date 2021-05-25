package com.ayonmotors.ayonmotors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyOrSellHistory extends AppCompatActivity {
    DatabaseReference reference;
    Functions functions;
    static ArrayList<bikemodel> selllist;
    static ArrayList<bikemodel> buylist;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_sell_history);
        reference = FirebaseDatabase.getInstance().getReference();
        functions = new Functions();
        selllist = new ArrayList<>();
        buylist = new ArrayList<>();
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        functions.showDialogue(this);
        reference.child("history").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                            if (dataSnapshot2.hasChild("sell")) {
                                for (DataSnapshot dataSnapshot3 : dataSnapshot2.child("sell").getChildren()) {
                                    bikemodel bikemodel = dataSnapshot3.getValue(com.ayonmotors.ayonmotors.bikemodel.class);
                                    selllist.add(bikemodel);

                                }
                            }
                            if (dataSnapshot2.hasChild("buy")) {
                                for (DataSnapshot dataSnapshot3 : dataSnapshot2.child("buy").getChildren()) {
                                    bikemodel bikemodel = dataSnapshot3.getValue(com.ayonmotors.ayonmotors.bikemodel.class);
                                    buylist.add(bikemodel);
                                }
                            }

                            functions.dismissdialogue();
                        }
                    }
                }
                SampleFragmentPagerAdapter pagerAdapter =
                        new SampleFragmentPagerAdapter(getSupportFragmentManager(), BuyOrSellHistory.this);
                viewPager.setAdapter(pagerAdapter);
                tabLayout.setupWithViewPager(viewPager);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        private final String[] tabTitles = new String[]{"Buy History", "Sell History"};

        Context context;

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            Fragment f;
            switch (position) {
                case 1:
                    f = new sellhistoryfragment(context,selllist);
                    break;
                case 0:
                default:
                    f = new buyhistoryfragment(context,buylist);
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

}