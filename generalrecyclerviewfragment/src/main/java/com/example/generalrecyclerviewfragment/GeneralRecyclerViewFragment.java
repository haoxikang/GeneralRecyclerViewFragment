package com.example.generalrecyclerviewfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public class GeneralRecyclerViewFragment extends Fragment implements GeneralContract.View {

    public static final String TAG = "GeneralRecyclerView";

    private GeneralPresenter mGeneralPresenter;
    private GeneralAdapter<RecyclerView.ViewHolder> mGeneralAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_recyclerview_fragment_layout, container, false);
        initViews(view);
        initListeners();
        mGeneralAdapter = mGeneralPresenter.getAdapter();
        mGeneralPresenter.onPresenterCreate();
        return view;
    }



    private void initViews(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

    }

    private void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGeneralPresenter.refreshData();
            }
        });
    }

    public void attachPresenter(GeneralPresenter presenter) {
        mGeneralPresenter = presenter;
        mGeneralPresenter.attachView(this);

    }

    @Override
    public void showLoadAnimation() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void closeLoadAnimation() {
            mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadNewDataFinish(@NonNull List newList) {
        closeLoadAnimation();
        if (!checkParameter(newList)) return;
        mGeneralPresenter.setPage(2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mGeneralAdapter);
        mGeneralAdapter.updateDataRefreshList(newList);


    }

    @Override
    public void loadNextDataFinish(List nextList) {
        closeLoadAnimation();
        if (!checkParameter(nextList)) return;
        mGeneralPresenter.increasePage();
        mGeneralAdapter.addDataAndRefreshList(nextList);
    }

    private boolean checkParameter(List list) {
        if (list == null) {
            Log.e(TAG, "List can not be null");
            return false;
        }

        if (mGeneralAdapter == null) {
            Log.e(TAG, "GeneralAdapter can not be null");
            return false;
        }
        return true;
    }
}
