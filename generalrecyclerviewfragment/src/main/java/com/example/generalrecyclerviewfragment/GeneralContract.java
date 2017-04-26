package com.example.generalrecyclerviewfragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public interface GeneralContract {
    interface View {

        void showLoadAnimation();

        void closeLoadAnimation();

        void refreshFinish(@NonNull List newList);

        void loadNextDataFinish(@NonNull List nextList);


        void initListeners();

        void loadError();
        void loadNextPageError();

    }

    interface Presenter {

        void setView(View view);

        void onPresenterCreate();


        void checkAndRefreshData();

        void checkAndLoadNextPageData();

        void refreshData();

        void loadNextPageData(int page);

        void refreshFinish(@NonNull List nextList);

        void loadNextPageFinish(@NonNull List nextList);

        void onRefreshError();

        void onLoadNextError();
    }
}
