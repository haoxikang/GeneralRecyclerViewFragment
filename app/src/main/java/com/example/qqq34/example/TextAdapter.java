package com.example.qqq34.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.generalrecyclerviewfragment.GeneralAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq34 on 2017/2/4.
 */

public class TextAdapter extends GeneralAdapter<TextAdapter.MyViewHolder> {
    private List<String> mStrings;

    public TextAdapter() {
        mStrings = new ArrayList<>();
    }

    @Override
    protected void updateDataRefreshList(List newList) {
        mStrings = newList;
        notifyDataSetChanged();
    }

    @Override
    protected void addDataAndRefreshList(List nextList) {
        mStrings.addAll(nextList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.view_list, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mStrings.get(position));
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
