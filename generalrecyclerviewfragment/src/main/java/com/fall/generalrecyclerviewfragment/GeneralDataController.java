package com.fall.generalrecyclerviewfragment;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq34 on 2017/2/6.
 */

public class GeneralDataController<ListType>  {
    public List<ListType> datas;
    private RecyclerView.Adapter innerAdapter;

    public GeneralDataController(RecyclerView.Adapter adapter) {
        datas = new ArrayList<>();
        innerAdapter = adapter;
    }

    protected void updateDataRefreshList(List<ListType> newList) {
        datas = newList;
        innerAdapter.notifyDataSetChanged();
    }

    protected void addDataAndRefreshList(List<ListType>nextList) {
        datas.addAll(nextList);
      innerAdapter .notifyDataSetChanged();
    }
}
