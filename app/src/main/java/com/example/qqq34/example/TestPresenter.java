package com.example.qqq34.example;

import android.os.Handler;
import android.util.Log;

import com.example.generalrecyclerviewfragment.GeneralAdapter;
import com.example.generalrecyclerviewfragment.GeneralPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public class TestPresenter extends GeneralPresenter {


    public TestPresenter(GeneralAdapter generalAdapter) {
        super(generalAdapter);
    }

    @Override
    public void refreshData() {
        new Handler().postDelayed(new Runnable() {

            public void run() {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    list.add("ddd"+i);
                }
                refreshFinish(list);
                //    onRefreshError("加载失败");
            }

        }, 2000);


    }

    @Override
    public void loadNextPageData(final int page) {
        Log.d("dddd", page + "  ");
        new Handler().postDelayed(new Runnable() {

            public void run() {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    list.add("b" + page + "   " + i);
                }
                loadNextPageFinish(list);
            }

        }, 2000);
    }
}
