package com.example.wuhanbus.service;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;

import com.example.wuhanbus.R;

public class BusStopQSService extends TileService {

    private static boolean STATE_ON = true;
    private static boolean STATE_OFF = false;
    private boolean toggleState = false;

    public BusStopQSService() {
        super();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        Log.e("daibin", "onTileAdded()============");
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        Log.e("daibin", "onTileRemoved()============");
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        Log.e("daibin", "onStartListening()============");
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        Log.e("daibin", "onStopListening()============");
    }

    @Override
    public void onClick() {
        super.onClick();
        Log.d("daibin", "onClick state = " + Integer.toString(getQsTile().getState()));
        Icon icon;
        if (toggleState) {
            toggleState = STATE_OFF;
            icon =  Icon.createWithResource(getApplicationContext(), R.drawable.bus_stop);
            getQsTile().setState(Tile.STATE_INACTIVE);// 更改成非活跃状态
        } else {
            toggleState = STATE_ON;
            icon = Icon.createWithResource(getApplicationContext(), R.drawable.bus_stop);
            getQsTile().setState(Tile.STATE_ACTIVE);//更改成活跃状态
        }

        getQsTile().setIcon(icon);//设置图标
        getQsTile().updateTile();//更新Tile
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
