package com.example.qqq34.example;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.generalrecyclerviewfragment.GeneralContract;
import com.example.generalrecyclerviewfragment.GeneralRecyclerViewFragment;

/**
 * Created by Administrator on 2017/4/26/026.
 */

public class TestFragment extends GeneralRecyclerViewFragment {
    private TestAdapter testAdapter;
    private TestPresenter testPresenter;
    private RecyclerView.LayoutManager layoutManager;

    @NonNull
    @Override
    protected GeneralContract.Presenter getPresenter() {
        if (testPresenter == null) {
            testPresenter = new TestPresenter();
        }
        return testPresenter;
    }

    @NonNull
    @Override
    protected RecyclerView.Adapter getAdapter() {
        if (testAdapter == null) {
            testAdapter = new TestAdapter();
        }
        return testAdapter;
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(getContext());
        }
        return layoutManager;
    }
}
