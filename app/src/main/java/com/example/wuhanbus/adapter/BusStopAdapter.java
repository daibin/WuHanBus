package com.example.wuhanbus.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wuhanbus.R;
import com.example.wuhanbus.bean.BusStopInfo;

import java.util.ArrayList;
import java.util.List;

public class BusStopAdapter extends BaseAdapter<BusStopInfo.DataBean,BusStopAdapter.ViewHolder> {
    private final List<BusStopInfo.DataBean.StopsBean> mStopsBeanList;
    private final List<String> mBusBeanList;
    private OnItemClickListener mOnItemClickListener;

    private int mChoicePosition = -1;

    public void setChoicePosition(int position) {
        mChoicePosition = position;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public BusStopAdapter(Context context) {
        super(context);
        mStopsBeanList = new ArrayList<>();
        mBusBeanList = new ArrayList<>();
    }

    @Override
    public void setData(BusStopInfo.DataBean dataBean){
        mStopsBeanList.clear();
        mStopsBeanList.addAll(dataBean.getStops());
//        mStopsBeanList = dataBean.getStops();
        mBusBeanList.clear();
        mBusBeanList.addAll(dataBean.getBuses());
        /*for(String str:mBusBeanList) {
            Log.e("daibin", "str: "+str);
        }*/
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTvBusStationOrder.setText(position+1+"");
        holder.mTvBusStationName.setText(mStopsBeanList.get(position).getStopName());
        holder.mTvBusStationOrder2.setText(position+1+"");
        holder.mTvBusStationName2.setText(mStopsBeanList.get(position).getStopName());

        if(position == 0){
            holder.mLinearLayout2.setVisibility(View.GONE);
        }else{
            holder.mLinearLayout2.setVisibility(View.VISIBLE);
        }
        String busStates = getBusStateByPosition(position);
        String[] busState = busStates.trim().split("\\|");
        int runningNum = Integer.parseInt(busState[0]);
        int arrivedNum = Integer.parseInt(busState[1]);
        setProperties(holder,
                0 != arrivedNum?mContext.getDrawable(R.drawable.bus_stop):null,
                1 < arrivedNum? busState[1]:"",
                0 != runningNum? mContext.getDrawable(R.drawable.bus_move):null,
                1 < runningNum? busState[0]:"");
        //holder.mImBus.setImageResource(R.drawable.bus_move_norm);
        holder.mImBusStation.setImageResource(R.drawable.bus_move_normal);

        if (position == mChoicePosition) {
            holder.mTvBusStationOrder.setTextColor(ContextCompat.getColor(mContext, R.color.onFocusColor));
            holder.mTvBusStationName.setTextColor(ContextCompat.getColor(mContext, R.color.onFocusColor));
        } else {
            holder.mTvBusStationOrder.setTextColor(ContextCompat.getColor(mContext, R.color.defaultFontsColor));
            holder.mTvBusStationName.setTextColor(ContextCompat.getColor(mContext, R.color.defaultFontsColor));
        }
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int focusPosition = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,focusPosition); // 2
//                    holder.mTvBusStationName.setTextColor(mContext.getResources().getColor(R.color.onFocusColor));
                }
            });
        }
    }

    private void setProperties(ViewHolder holder, Drawable drawable1,String runningNum, Drawable drawable2,String arrivedNum){
        holder.mImBus.setImageDrawable(drawable1);
        holder.mImBus2.setImageDrawable(drawable2);
        holder.mTvBusNumber.setText(runningNum);
        holder.mTvBusNumber2.setText(arrivedNum);
    }

    private String getBusStateByPosition(int position) {
        int runningCount = 0;
        int arrivedCount = 0;
        if (mBusBeanList == null || mBusBeanList.isEmpty()) {
            return "0|0";
        }
        for(String busBean:mBusBeanList){
            String[] busInfo = busBean.trim().split("\\|");
            int siteSerial = Integer.parseInt(busInfo[2]) - 1;
            if (position == siteSerial) {
                if(0 == Integer.parseInt(busInfo[3])){
                    runningCount++;
                }else if(1 == Integer.parseInt(busInfo[3])){
                    arrivedCount++;
                }
            }
        }
        return runningCount+"|"+arrivedCount;
    }

    @Override
    public int getItemCount() {
        return mStopsBeanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImBus;
        private final ImageView mImBusStation;
        private final TextView mTvBusStationName;
        private final TextView mTvBusStationOrder;
        private final ImageView mImBus2;
        private final ImageView mImBusStation2;
        private final TextView mTvBusStationName2;
        private final TextView mTvBusStationOrder2;
        private final LinearLayout mLinearLayout2;
        private final TextView mTvBusNumber;
        private final TextView mTvBusNumber2;

        public ViewHolder(View view){
            super(view);
            mImBus = view.findViewById(R.id.im_bus);
            mImBusStation = view.findViewById(R.id.im_bus_station);
            mTvBusStationName = view.findViewById(R.id.tv_bus_station_name);
            mTvBusStationOrder = view.findViewById(R.id.tv_bus_station_order);
            mImBus2 = view.findViewById(R.id.im_bus_2);
            mImBusStation2 = view.findViewById(R.id.im_bus_station_2);
            mTvBusStationName2 = view.findViewById(R.id.tv_bus_station_name_2);
            mTvBusStationOrder2 = view.findViewById(R.id.tv_bus_station_order_2);
            mLinearLayout2 = view.findViewById(R.id.ll_bus_station_info_2);
            mTvBusNumber = view.findViewById(R.id.tv_bus_number);
            mTvBusNumber2 = view.findViewById(R.id.tv_bus_number_2);
        }
    }
}

