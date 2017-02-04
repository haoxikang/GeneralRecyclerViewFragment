package com.example.qqq34.example;

import android.support.v7.widget.RecyclerView;

import com.example.generalrecyclerviewfragment.GeneralAdapter;
import com.example.generalrecyclerviewfragment.GeneralPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public class Presenter extends GeneralPresenter {
    public Presenter(GeneralAdapter generalAdapter) {
        super(generalAdapter);
    }

    @Override
    public void refreshData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("diddi" + i);
        }
        mView.loadNewDataFinish(list);
    }

    @Override
    public void loadNextPageData() {

    }
}
