package com.fall.generalrecyclerviewfragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25/025.
 */

public abstract class BaseGeneraFragment extends Fragment implements GeneralContract.View {

    private RecyclerView.Adapter adapter;
    protected RecyclerView recyclerView;
    private GeneralDataController generalDataController;
    public static final String TAG = "BaseGeneraFragment";



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getRecyclerView();
        if (getPresenter() == null || getAdapter() == null || getLayoutManager() == null) {
            Log.e(TAG, "The argument can not be null");
        }
        if (getAdapter() instanceof GeneralAdapter) {
            adapter = getAdapter();
            generalDataController = ((GeneralAdapter) adapter).getGeneralDataController();
        } else {
            Log.e(TAG, "The adapter must implements GeneralAdapter");
        }
        if (getItemDecoration() != null) {
            recyclerView.addItemDecoration(getItemDecoration());
        }
        if (recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(getLayoutManager());
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }


        getPresenter().setView(this);
        initListeners();
        getPresenter().onPresenterCreate();
    }


    @Override
    public void refreshFinish(@NonNull List newList) {

        if (!checkParameter(newList)) return;
        if (recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(getLayoutManager());
        }
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        generalDataController.updateDataRefreshList(newList);


    }

    @Override
    public void loadNextDataFinish(@NonNull List nextList) {
        if (!checkParameter(nextList)) return;
        generalDataController.addDataAndRefreshList(nextList);
    }

    private boolean checkParameter(List list) {
        if (list == null) {
            Log.e(TAG, "List can not be null");
            return false;
        }

        if (adapter == null) {
            Log.e(TAG, "Adapter can not be null");
            return false;
        }
        return true;
    }

    @Nullable
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @NonNull
    protected abstract GeneralContract.Presenter getPresenter();


    @NonNull
    protected abstract RecyclerView.Adapter getAdapter();


    @NonNull
    protected abstract RecyclerView.LayoutManager getLayoutManager();


    protected abstract RecyclerView getRecyclerView();
}
