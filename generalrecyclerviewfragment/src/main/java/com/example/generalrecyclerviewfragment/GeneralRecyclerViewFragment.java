package com.example.generalrecyclerviewfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public class GeneralRecyclerViewFragment extends Fragment implements GeneralContract.View {

    public static final String TAG = "GeneralRecyclerView";

    private GeneralContract.Presenter mGeneralPresenter;
    private RecyclerView.Adapter mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FrameLayout mFrameLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_recyclerview_fragment_layout, container, false);
        initViews(view);
        initListeners();
        mGeneralPresenter.onPresenterCreate();
        return view;
    }


    private void initViews(View view) {
        mFrameLayout = (FrameLayout) view.findViewById(R.id.CustomLayout);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

    }

    private void initListeners() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                try {
                    if (getLayoutManagerOrientation() && !recyclerView.canScrollVertically(1)) {
                        mGeneralPresenter.checkAndLoadNextPageData();
                    } else if (!getLayoutManagerOrientation() && !recyclerView.canScrollHorizontally(1)) {
                        mGeneralPresenter.checkAndLoadNextPageData();
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGeneralPresenter.checkAndRefreshData();
            }
        });
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
    public void refreshFinish(@NonNull List newList) {

        if (!checkParameter(newList)) return;
        if (mRecyclerView.getLayoutManager() == null) {
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        if (mRecyclerView.getAdapter() == null) {
            mRecyclerView.setAdapter(mAdapter);
        }

        ((GeneralAdapter) mAdapter).getGeneralDataController().updateDataRefreshList(newList);


    }

    @Override
    public void loadNextDataFinish(@NonNull List nextList) {
        if (!checkParameter(nextList)) return;
        ((GeneralAdapter) mAdapter).getGeneralDataController().addDataAndRefreshList(nextList);
    }

    @Override
    public void setPresenter(GeneralContract.Presenter presenter) {
        mGeneralPresenter = presenter;
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(mSwipeRefreshLayout, message, Snackbar.LENGTH_SHORT).show();
    }



    private boolean checkParameter(List list) {
        if (list == null) {
            Log.e(TAG, "List can not be null");
            return false;
        }

        if (mAdapter == null) {
            Log.e(TAG, "Adapter can not be null");
            return false;
        }
        return true;
    }


    public void initialize(@NonNull GeneralContract.Presenter presenter, @NonNull RecyclerView.Adapter adapter, @NonNull RecyclerView.LayoutManager layoutManager) {

        if (presenter == null || adapter == null || layoutManager == null) {
            Log.e(TAG, "The argument can not be null");
        }
        mLayoutManager = layoutManager;
        presenter.setView(this);
        setPresenter(presenter);
        if (adapter instanceof GeneralAdapter) {
            mAdapter = adapter;
        } else {
            Log.e(TAG, "The adapter must implements GeneralAdapter");
        }
    }

    private boolean getLayoutManagerOrientation() throws Exception {
        if (mLayoutManager instanceof LinearLayoutManager) {
            return (((LinearLayoutManager) mLayoutManager).getOrientation() == LinearLayoutManager.VERTICAL) ? true : false;
        } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            return (((StaggeredGridLayoutManager) mLayoutManager).getOrientation() == StaggeredGridLayoutManager.VERTICAL) ? true : false;
        } else {
            throw new Exception("If you use the default SwipeRefreshLayout. " +
                    "You can only select one of the LinearLayoutManager, StaggeredGridLayoutManager, and GridLayoutManager.");
        }

    }
}
