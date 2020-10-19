package com.example.wuhanbus.service;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.wuhanbus.activity.MainActivity;
import com.example.wuhanbus.bean.StopInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class BusStopWidgetDBSetService extends RemoteViewsService{
    private ArrayList<StopInfo> data ;

    public BusStopWidgetDBSetService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this,intent);
    }

    public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

        private Context mContext;
        private int mAppWidgetId;

        public ListRemoteViewsFactory(Context context,Intent intent) {
            mContext=context;
            mAppWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
            initGridViewData();
        }

        private void initGridViewData() {
            data = new ArrayList<StopInfo>();
            SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.FAVORITE_LINE,Context.MODE_PRIVATE);
            Map<String, ?> map = sharedPreferences.getAll();
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                /*if(!val.isEmpty()) {
                    String[] valInfo = val.split("_");
                    FavoriteLineInfo favoriteLineInfo = new FavoriteLineInfo();
                    favoriteLineInfo.setLineName(valInfo[0]);
                    favoriteLineInfo.setDirection(Integer.parseInt(valInfo[1]));
                    favoriteLineInfo.setChoicePosition(valInfo[2]);
                    data.add(favoriteLineInfo);
                }*/
            }
            /*for (int i=0; i<9; i++) {
                String[] busInfo = {"70"+i+"路","光谷八路未来科技城","珞瑜东路森林公园",i+"",i*300+""};
                data.add(busInfo);
            }*/

        }

        @Override
        public void onDataSetChanged() {
            initGridViewData();
        }

        @Override
        public void onDestroy() {
            data.clear();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            //FavoriteLineInfo busInfo= data.get(position);
            //RemoteViews rv=new RemoteViews(getPackageName(), R.layout.item_recycler_widget);
            //rv.setTextViewText(R.id.w_tv_lineName,busInfo.getLineName());
            /*rv.setTextViewText(R.id.w_tv_endStopName,"开    往："+busInfo[1]);
            rv.setTextViewText(R.id.w_tv_currentStopName,"候车站："+busInfo[2]);
            if(Integer.parseInt(busInfo[4])<10){
                rv.setTextViewText(R.id.w_tv_needTime,"已到站");
            }else{
                rv.setTextViewText(R.id.w_tv_needTime,busInfo[3]+"分钟");
            }
            rv.setTextViewText(R.id.w_tv_numStopAndDistance,busInfo[4]+"m");*/
            return null;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
