package com.example.wuhanbus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wuhanbus.R;
import com.example.wuhanbus.bean.BusLineInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends BaseAdapter<List<BusLineInfo.LinesBean>,SearchAdapter.ViewHolder> {


    private final List<BusLineInfo.LinesBean> mData;
    private SearchAdapter.OnItemClickListener mOnItemClickListener;

    public SearchAdapter(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    public void setOnItemClickListener(SearchAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = mInflater.inflate(R.layout.item_search_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.tvLineName.setText(mData.get(position).getLineName());
        viewHolder.tvDestStopName.setText("开往："+mData.get(position).getEndStopName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int focusPosition = viewHolder.getLayoutPosition(); // 1
                mOnItemClickListener.onItemClick(viewHolder.itemView,focusPosition); // 2
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void setData(List<BusLineInfo.LinesBean> data) {
        mData.clear();
        mData.addAll(data);
        mData.size();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_line_name)
        TextView tvLineName;
        @BindView(R.id.tv_dest_stop_name)
        TextView tvDestStopName;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

