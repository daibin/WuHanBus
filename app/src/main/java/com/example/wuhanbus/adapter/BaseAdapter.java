package com.example.wuhanbus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

public abstract class BaseAdapter<K,T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> implements ISetData<K>{

    protected Context mContext;
    protected LayoutInflater mInflater;

    public BaseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
}
