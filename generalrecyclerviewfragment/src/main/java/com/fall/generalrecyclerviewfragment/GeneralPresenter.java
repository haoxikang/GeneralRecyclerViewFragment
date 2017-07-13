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
        afterLoad();
        page++;
        mView.loadNextDataFinish(nextList);
    }

    @Override
    public void loadLastPageDataFinish(@NonNull List nextList) {
        canLoad = true;
        canLoadNext = false;
        mView.closeLoadAnimation();
        mView.loadNextDataFinish(nextList);
    }

    @Override
    public void refreshFinishNoNext(@NonNull List nextList) {
        canLoadNext = true;
        canLoad = false;
        mView.closeLoadAnimation();
        page = 2;
        mView.refreshFinish(nextList);
    }

    @Override
    public void refreshFinish(@NonNull List nextList) {
        canLoadNext = true;
        afterLoad();
        page = 2;
        mView.refreshFinish(nextList);
    }

    @Override
    public void onPresenterCreate() {
        checkAndRefreshData();
    }


    @Override
    public void checkAndLoadNextPageData() {
        if (canLoad && canLoadNext) {
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
        afterLoad();
    }

    @Override
    public void onRefreshError() {
        afterLoad();
        mView.loadError();
    }

    @Override
    public void onRefreshError(String s) {
        afterLoad();
        mView.loadError(s);
    }

    @Override
    public void onRefreshError(String s, int res) {
        afterLoad();
        mView.loadError(s, res);
    }

    @Override
    public void onLoadNextError() {
        afterLoad();
        mView.loadNextPageError();
    }

    @Override
    public void onLoadNextError(String s) {
        afterLoad();
        mView.loadNextPageError(s);
    }

    @Override
    public void onLoadNextError(String s, int res) {
        afterLoad();
        mView.loadNextPageError(s, res);
    }

    @Override
    public void onReloadError() {
        afterLoad();
        mView.reLoadError();
    }

    @Override
    public void onReloadError(String s) {
        afterLoad();
        mView.reLoadError(s);
    }

    @Override
    public void onReloadError(String s, int res) {
        afterLoad();
        mView.reLoadError(s, res);
    }

    private void beforeLoad() {
        mView.showLoadAnimation();
        canLoad = false;
    }

    private void afterLoad() {
        canLoad = true;
        mView.closeLoadAnimation();
    }

    @Override
    public void noDataLoad() {
        afterLoad();
        canLoadNext = false;
        canLoad=false;
        mView.noDataLoad();
    }

    @Override
    public void noDataLoad(String s) {
        afterLoad();
        canLoadNext = false;
        canLoad=false;
        mView.noDataLoad(s);
    }

    @Override
    public void noDataLoad(String s, int res) {
        afterLoad();
        canLoadNext = false;
        canLoad=false;
        mView.noDataLoad(s, res);
    }
}
