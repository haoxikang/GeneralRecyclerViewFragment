package com.fall.qqq34.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fall.generalrecyclerviewfragment.GeneralAdapter;
import com.fall.generalrecyclerviewfragment.GeneralDataController;

/**
 * Created by qqq34 on 2017/2/4.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> implements GeneralAdapter {

    private GeneralDataController<String> mStringGeneralDataController;

    public TestAdapter() {
        mStringGeneralDataController = new GeneralDataController<>(this);
    }
    @Override
    public GeneralDataController getGeneralDataController() {
        return mStringGeneralDataController;
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
        holder.tv.setText(mStringGeneralDataController.datas.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringGeneralDataController.datas.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
    public void remove(int position){
        mStringGeneralDataController.datas.remove(position);
        notifyItemRemoved(position);
    }
}
