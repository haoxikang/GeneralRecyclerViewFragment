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

        TestAdapter textAdapter = new TestAdapter();
        GeneralRecyclerViewFragment generalRecyclerViewFragment = new GeneralRecyclerViewFragment();
        generalRecyclerViewFragment.initialize(new TestPresenter(textAdapter));

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout, generalRecyclerViewFragment);
        transaction.commit();
    }
}
