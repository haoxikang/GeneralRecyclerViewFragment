package com.example.generalrecyclerviewfragment;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public abstract class GeneralPresenter implements GeneralContract.Presenter {

    private int page = 1;
    protected GeneralContract.View mView;
    private GeneralAdapter mGeneralAdapter;

    private boolean canLoad = true;


    @Override
    public void setView(GeneralContract.View view) {
        mView = view;
    }

    public GeneralPresenter(GeneralAdapter generalAdapter) {
        mGeneralAdapter = generalAdapter;
    }

    @Override
    public GeneralAdapter getAdapter() {
        return mGeneralAdapter;
    }

    @Override
    public void loadNextPageFinish(@NonNull List nextList) {
        canLoad = true;
        mView.closeLoadAnimation();
        page++;
        mView.loadNextDataFinish(nextList);
    }

    @Override
    public void refreshFinish(@NonNull List nextList) {
        canLoad = true;
        mView.closeLoadAnimation();
        page=2;
        mView.refreshFinish(nextList);
    }

    @Override
    public void onPresenterCreate() {
        checkAndRefreshData();
    }


    @Override
    public void checkAndLoadNextPageData() {
        if (canLoad) {
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

    private void beforeLoad() {
        mView.showLoadAnimation();
        canLoad = false;
    }

}
