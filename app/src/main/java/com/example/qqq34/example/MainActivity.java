package com.example.qqq34.example;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.generalrecyclerviewfragment.GeneralRecyclerViewFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextAdapter textAdapter = new TextAdapter();

        GeneralRecyclerViewFragment generalRecyclerViewFragment = new GeneralRecyclerViewFragment();
        generalRecyclerViewFragment.initialize(new Presenter(textAdapter));

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout, generalRecyclerViewFragment);
        transaction.commit();
    }
}
