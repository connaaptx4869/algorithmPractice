package com.ihaozhuo.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BasicRecyclerAdapter extends RecyclerView.Adapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public BasicRecyclerAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_recyclerview, parent, false);
        return new BasicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class BasicViewHolder extends RecyclerView.ViewHolder {

        public BasicViewHolder(View itemView) {
            super(itemView);
        }
    }
}
