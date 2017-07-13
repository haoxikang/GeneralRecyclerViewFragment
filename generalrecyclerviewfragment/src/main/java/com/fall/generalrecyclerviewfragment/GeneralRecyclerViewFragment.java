package com.fall.generalrecyclerviewfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by qqq34 on 2017/2/4.
 */

public abstract class GeneralRecyclerViewFragment extends BaseGeneraFragment {


    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    protected FrameLayout errorLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_recyclerview_fragment_layout, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        errorLayout = (FrameLayout) view.findViewById(R.id.error_layout);
        return view;
    }

    @Override
    public void initListeners() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                try {
                    if (getLayoutManagerOrientation() && !recyclerView.canScrollVertically(1)) {
                        getPresenter().checkAndLoadNextPageData();
                    } else if (!getLayoutManagerOrientation() && !recyclerView.canScrollHorizontally(1)) {
                        getPresenter().checkAndLoadNextPageData();
                    }
                } catch (Exception e) {
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().checkAndRefreshData();
            }
        });
    }


    @Override
    public void showLoadAnimation() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void closeLoadAnimation() {
        swipeRefreshLayout.setRefreshing(false);
    }


    private boolean getLayoutManagerOrientation() throws Exception {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            return (((LinearLayoutManager) getLayoutManager()).getOrientation() == LinearLayoutManager.VERTICAL) ? true : false;
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            return (((StaggeredGridLayoutManager) getLayoutManager()).getOrientation() == StaggeredGridLayoutManager.VERTICAL) ? true : false;
        } else {
            throw new Exception("If you use the default SwipeRefreshLayout. " +
                    "You can only select one of the LinearLayoutManager, StaggeredGridLayoutManager, and GridLayoutManager.");
        }

    }


    @Override
    public void loadError() {
        loadError("加载失败请点击屏幕重试");

    }

    @Override
    public void loadError(String s) {
        loadError(s, -1);

    }

    @Override
    public void loadError(int res) {
        loadError(getString(res), -1);
    }

    @Override
    public void loadError(String s, int res) {
        if (errorLayout.getChildCount() == 0) {
            TextView textView = new TextView(getContext());
            textView.setText(s);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            errorLayout.addView(textView, layoutParams);
            errorLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPresenter().checkAndRefreshData();
                    errorLayout.setVisibility(View.GONE);
                }
            });
        }
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadNextPageError() {
        loadNextPageError("加载失败");

    }

    @Override
    public void loadNextPageError(String s) {
        loadNextPageError(s, -1);
    }

    @Override
    public void loadNextPageError(int res) {
        loadNextPageError(getString(res), -1);
    }

    @Override
    public void loadNextPageError(String s, int res) {
        Snackbar.make(recyclerView, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void reLoadError() {
        reLoadError("加载失败");
    }

    @Override
    public void reLoadError(String s) {
        reLoadError(s, -1);
    }

    @Override
    public void reLoadError(int res) {
        reLoadError(getString(res), -1);
    }

    @Override
    public void reLoadError(String s, int res) {
        Snackbar.make(recyclerView, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void noDataLoad() {
        noDataLoad("数据为空");
    }

    @Override
    public void noDataLoad(String s) {
        noDataLoad("数据为空", -1);
    }

    @Override
    public void noDataLoad(int res) {
        noDataLoad(getString(res), -1);
    }

    @Override
    public void noDataLoad(String s, int res) {
        Snackbar.make(recyclerView, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
