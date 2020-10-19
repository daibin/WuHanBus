package com.example.wuhanbus.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wuhanbus.MyApplication;
import com.example.wuhanbus.R;
import com.example.wuhanbus.bean.BusStopInfo;
import com.example.wuhanbus.bean.StopInfo;
import com.example.wuhanbus.utils.AMapUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import database.greenDao.db.DaoSession;
import database.greenDao.db.StopInfoDao;

public class FavoriteAdapter extends BaseAdapter<List<BusStopInfo>,FavoriteAdapter.ViewHolder>{
    private final List<BusStopInfo> mData;
    private final List<String> mBusBeanList;
    private OnItemClickListener mOnItemClickListener;
    private double destLat;
    private double destLng;
    private double startLat;
    private double startLng;
    private final StopInfoDao mStopInfoDao;
    private static final int SPEED = 380;
    private final Handler mHandler;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public FavoriteAdapter(Context context) {
        super(context);
        mData = new ArrayList<>();
        mBusBeanList = new ArrayList<>();
        MyApplication app = MyApplication.getInstance();
        DaoSession mDaoSession = app.getDaoSession();
        mStopInfoDao = mDaoSession.getStopInfoDao();
        HandlerThread mHandlerThread = new HandlerThread("saveStopInfo Thread");
        mHandlerThread.start();

        mHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    saveStopInfo(mData.get(msg.arg1).getData());
                }
            }
        };
    }

    @Override
    public void setData(List<BusStopInfo> data) {
        mData.clear();
        mData.addAll(data);
        mData.size();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_widget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Message msg = Message.obtain();
        msg.what =1;
        msg.arg1 = holder.getAdapterPosition();
        mHandler.sendMessage(msg);
        //saveStopInfo(mData.get(position).getData());
        double distance = -1;
        BusStopInfo.DataBean dataBean = mData.get(position).getData();
        int currentStopIndex = dataBean.getCurrentStopId();
        String currentStopId = mData.get(position).getData().getStops().get(currentStopIndex).getStopId()+"*"+mData.get(position).getData().getDirection();
        List<StopInfo> stopInfos= mStopInfoDao.queryRaw("where STOP_ID = ?", currentStopId);
        for(StopInfo stopInfo:stopInfos){
            destLat = stopInfo.getLat();
            destLng = stopInfo.getLng();
        }
        mBusBeanList.clear();
        mBusBeanList.addAll(dataBean.getBuses());
        Integer[] numStopAndDistance = getBusStateByPosition(currentStopIndex);
        distance = AMapUtils.calculateLineDistance(startLat,startLng,destLat,destLng);
        holder.wTvLineName.setText(dataBean.getLineName()+"路");
        holder.wTvEndStopName.setText("开    往    "+dataBean.getEndStopName());
        holder.wTvCurrentStopName.setText("候车站    "+dataBean.getStops().get(currentStopIndex).getStopName());
        int time = (int)Math.round(distance/SPEED) == 0 ? 1:(int)Math.round(distance/SPEED);
        if(0 == numStopAndDistance[1] && 0 == numStopAndDistance[0]){
            setProperties(holder,View.VISIBLE,time+"分","即将到站/"+(int)distance+"米",mContext.getColor(R.color.onFocusColor),22,12);
        }else if(1 == numStopAndDistance[1] && 0 == numStopAndDistance[0]){
            setProperties(holder,View.GONE,"已到站","已到站",mContext.getColor(R.color.colorAccent),22,22);
        }else if(-1 == numStopAndDistance[0]){
            setProperties(holder,View.GONE,"还未发车","还未发车",mContext.getColor(R.color.colorPrimary),22,22);
        }else{
            setProperties(holder,View.VISIBLE,/*numStopAndDistance[0]*3*/time+"分",numStopAndDistance[0]+"站/"+(int)distance+"米",mContext.getColor(R.color.colorPrimary),22,12);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int focusPosition = holder.getLayoutPosition(); // 1
                mOnItemClickListener.onItemClick(holder.itemView,focusPosition); // 2
            }
        });
    }

    private void save(BusStopInfo.DataBean dataBean,int index,double lat,double lng){
        //"31373|12|6|0|114.26333823574225|30.59596213940074"
        StopInfo stopInfo = new StopInfo();
        stopInfo.setLineName(dataBean.getLineName());
        stopInfo.setLineId(dataBean.getLineId());
        stopInfo.setLineNo(dataBean.getLineNo());
        stopInfo.setStopId(dataBean.getStops().get(index).getStopId()+"*"+dataBean.getDirection());
        stopInfo.setStopName(dataBean.getStops().get(index).getStopName());
        stopInfo.setLat(lat);
        stopInfo.setLng(lng);
        List<StopInfo> stopInfos= mStopInfoDao.queryRaw("where STOP_ID = ?", stopInfo.getStopId());
        if(stopInfos.isEmpty()){
            try {
                mStopInfoDao.insert(stopInfo);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            for(StopInfo stopInfoSaved:stopInfos){
                stopInfoSaved.setLat(stopInfo.getLat()*0.2+stopInfoSaved.getLat()*0.8);
                stopInfoSaved.setLng(stopInfo.getLng()*0.2+stopInfoSaved.getLng()*0.8);
                mStopInfoDao.update(stopInfoSaved);
            }
        }
    }

    private void saveStopInfo(BusStopInfo.DataBean dataBean) {
        for(String busBean:dataBean.getBuses()){
            String[] busInfo = busBean.trim().split("\\|");
            if(1 == Integer.parseInt(busInfo[3])) {
                int index = Integer.parseInt(busInfo[2])-1;
                save(dataBean, index, Double.parseDouble(busInfo[5]), Double.parseDouble(busInfo[4]));
            }
        }
    }

    private void setProperties(FavoriteAdapter.ViewHolder holder,int isVisible,String str1,String str2,int color1,float size1,float size2){
        holder.wTvNeedTime.setVisibility(isVisible);
        holder.wTvNeedTime.setText(str1);
        holder.wTvNumStopAndDistance.setText(str2);
        holder.wTvNeedTime.setTextColor(color1);
        holder.wTvNumStopAndDistance.setTextColor(color1);
        holder.wTvNeedTime.setTextSize(size1);
        holder.wTvNumStopAndDistance.setTextSize(size2);
    }

    private Integer[] getBusStateByPosition(int currentStopIndex) {
        int state = -1;
        int arrivedState = -1;
        if (mBusBeanList == null || mBusBeanList.isEmpty()) {
            return new Integer[]{state,arrivedState};
        }
        for(String busBean:mBusBeanList){
            String[] busInfo = busBean.trim().split("\\|");
            if((Integer.parseInt(busInfo[2])-1) >=0 && (Integer.parseInt(busInfo[2])-1) <= currentStopIndex){
                state = currentStopIndex - (Integer.parseInt(busInfo[2])-1);
                arrivedState = Integer.parseInt(busInfo[3]);
                startLat = Double.parseDouble(busInfo[5]);
                startLng = Double.parseDouble(busInfo[4]);
            }
        }
        return new Integer[]{state,arrivedState};
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.w_tv_lineName)
        TextView wTvLineName;
        @BindView(R.id.w_tv_endStopName)
        TextView wTvEndStopName;
        @BindView(R.id.w_tv_currentStopName)
        TextView wTvCurrentStopName;
        @BindView(R.id.w_tv_needTime)
        TextView wTvNeedTime;
        @BindView(R.id.w_tv_numStopAndDistance)
        TextView wTvNumStopAndDistance;
        @BindView(R.id.ll_recycler_widget_item_layout)
        LinearLayout llRecyclerWidgetItemLayout;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

