package com.example.generalrecyclerviewfragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public abstract class GeneralAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected abstract void updateDataRefreshList(List newList);
    protected abstract void addDataAndRefreshList(List nextList);
}
