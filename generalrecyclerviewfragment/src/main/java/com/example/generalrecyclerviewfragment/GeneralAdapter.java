package com.example.generalrecyclerviewfragment;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public abstract   class GeneralAdapter<VH extends RecyclerView.ViewHolder,ListType> extends RecyclerView.Adapter<VH> {

    protected  List<ListType> datas;

    public GeneralAdapter() {
       datas = new ArrayList<>();
    }

    protected void updateDataRefreshList(List newList) {
        datas = newList;
        notifyDataSetChanged();
    }

    protected void addDataAndRefreshList(List nextList) {
        datas.addAll(nextList);
        notifyDataSetChanged();
    }
}
