package com.example.generalrecyclerviewfragment;

import android.support.v7.widget.RecyclerView;

/**
 * Created by qqq34 on 2017/2/4.
 */

public abstract class GeneralPresenter implements GeneralContract.Presenter {

    private int page = 1;
    protected GeneralContract.View mView;
    private GeneralAdapter<RecyclerView.ViewHolder> mGeneralAdapter;

    public GeneralPresenter(GeneralAdapter<RecyclerView.ViewHolder> generalAdapter) {
        mGeneralAdapter = generalAdapter;
    }

    public void attachView(GeneralContract.View vew) {
        mView = vew;
    }

    @Override
    public GeneralAdapter<RecyclerView.ViewHolder> getAdapter() {
        return mGeneralAdapter;
    }

    @Override
    public void onPresenterCreate() {
        refreshData();
        mView.showLoadAnimation();
    }

    public void setPage(int page) {
        this.page = page;
    }
    public void increasePage(){
        page++;
    }
}
