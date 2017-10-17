package com.fall.generalrecyclerviewfragment;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

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

        void loadError(String s);

        void loadError(@StringRes int res);

        void loadError(String s, @DrawableRes int res);

        void hideErrorLayout();

        void loadNextPageError();

        void loadNextPageError(String s);
        void loadNextPageError(@StringRes int res);

        void loadNextPageError(String s, @DrawableRes int res);

        void reLoadError();

        void reLoadError(String s);
        void reLoadError(@StringRes int res);

        void reLoadError(String s, @DrawableRes int res);

        void noDataLoad();

        void noDataLoad(String s);
        void noDataLoad(@StringRes int res);

        void noDataLoad(String s, @DrawableRes int res);
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

        void loadLastPageDataFinish(@NonNull List nextList);

        void onRefreshError();

        void onRefreshError(String s);

        void onRefreshError(@StringRes int res);

        void onRefreshError(String s, @DrawableRes int res);

        void onReloadError();

        void onReloadError(String s);

        void onReloadError(@StringRes int res);

        void onReloadError(String s, @DrawableRes int res);

        void onLoadNextError();

        void onLoadNextError(String s);

        void onLoadNextError(@StringRes int res);

        void onLoadNextError(String s, @DrawableRes int res);

        void stopLoading();

        void refreshFinishNoNext(@NonNull List nextList);

        void noDataLoad();

        void noDataLoad(String s);

        void noDataLoad(@StringRes int res);

        void noDataLoad(String s, @DrawableRes int res);
    }
}
