package com.example.generalrecyclerviewfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public interface GeneralContract {
    interface View {

        void setAdapter(RecyclerView.Adapter adapter);

        void showLoadAnimation();

        void closeLoadAnimation();

        void refreshFinish(@NonNull List newList);

        void loadNextDataFinish(@NonNull List nextList);

        void setPresenter(Presenter presenter);
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

        void onRefreshError(String message);

        void onLoadNextError(String message);

    }
}
