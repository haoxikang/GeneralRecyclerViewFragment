package com.example.generalrecyclerviewfragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public interface GeneralContract {
    interface View  {

        void showLoadAnimation();

        void closeLoadAnimation();

        void loadNewDataFinish(List newList);

        void loadNextDataFinish(List nextList);
    }

    interface Presenter  {

        void onPresenterCreate();

        GeneralAdapter<RecyclerView.ViewHolder> getAdapter();

        void refreshData();

        void loadNextPageData();

    }
}
