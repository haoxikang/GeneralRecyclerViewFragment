package com.fall.generalrecyclerviewfragment;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public abstract class GeneralPresenter implements GeneralContract.Presenter {

    public static final String TAG = "GeneralPresenter";

    private int page = 1;
    private GeneralContract.View mView;

    private boolean canLoad = true;
    private boolean canLoadNext = true;

    @Override
    public void setView(GeneralContract.View view) {
        mView = view;
    }

    @Override
    public void loadNextPageFinish(@NonNull List nextList) {
        canLoad = true;
        mView.closeLoadAnimation();
        page++;
        mView.loadNextDataFinish(nextList);
    }

    @Override
    public void loadLastPageDataFinish(@NonNull List nextList) {
        canLoad = true;
        canLoadNext = false;
        mView.closeLoadAnimation();
        mView.loadLastPageDataFinish(nextList);
    }

    @Override
    public void refreshFinish(@NonNull List nextList) {
        canLoadNext = true;
        canLoad = true;
        mView.closeLoadAnimation();
        page = 2;
        mView.refreshFinish(nextList);
    }

    @Override
    public void onPresenterCreate() {
        checkAndRefreshData();
    }


    @Override
    public void checkAndLoadNextPageData() {
        if (canLoad&&canLoadNext) {
            beforeLoad();
            loadNextPageData(page);
        }
    }

    @Override
    public void checkAndRefreshData() {
        if (canLoad) {
            beforeLoad();
            refreshData();
        }
    }

    @Override
    public void stopLoading() {
        canLoad = true;
        mView.closeLoadAnimation();
    }

    @Override
    public void onRefreshError() {
        canLoad = true;
        mView.closeLoadAnimation();
        mView.loadError();
    }

    @Override
    public void onLoadNextError() {
        canLoad = true;
        mView.closeLoadAnimation();
        mView.loadNextPageError();
    }

    @Override
    public void onReloadError() {
        canLoad = true;
        mView.closeLoadAnimation();
        mView.reLoadError();
    }

    private void beforeLoad() {
        mView.showLoadAnimation();
        canLoad = false;
    }

}
