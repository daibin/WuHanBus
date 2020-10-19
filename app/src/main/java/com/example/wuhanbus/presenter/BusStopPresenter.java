package com.example.wuhanbus.presenter;

import android.util.Log;

import com.example.wuhanbus.bean.BusStopInfo;
import com.example.wuhanbus.service.RetrofitService;
import com.example.wuhanbus.view.ILoadDataView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class BusStopPresenter implements IBasePresenter{
    private final ILoadDataView mView;
    private String mLine;
    private int mDirection;
    private final List<BusStopInfo> mBusStopInfos;

    public BusStopPresenter(ILoadDataView mView) {
        this.mView = mView;
        mBusStopInfos = new ArrayList<>();
    }

    public void setLineDirection(String mLine, int mDirection){
        this.mLine = mLine;
        this.mDirection = mDirection;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getBusStopInfo(mLine,mDirection)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .doOnNext(new Action1<BusStopInfo>() {
                    @Override
                    public void call(BusStopInfo newsDetailBean) {

                    }
                })
                .subscribeOn(Schedulers.io())
                //.compose(mView.<BusStopInfo>bindToLife())
                .subscribe(new Subscriber<BusStopInfo>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading(mBusStopInfos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        Log.e("daibin",e.toString());
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(BusStopInfo busStopInfo) {
                        mView.loadData(busStopInfo);
                    }
                });
    }

    @Override
    public void getMoreData(List<String> params) {

    }
}
