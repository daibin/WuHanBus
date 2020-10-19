package com.example.wuhanbus.presenter;

import android.util.Log;

import com.example.wuhanbus.bean.BusLineInfo;
import com.example.wuhanbus.service.RetrofitService;
import com.example.wuhanbus.view.ILoadLinesInfoDataView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SearchPresenter implements IBasePresenter{
    private final ILoadLinesInfoDataView mView;
    private String mKeyword;
    private List<BusLineInfo> mBusLineInfos;

    public SearchPresenter(ILoadLinesInfoDataView mView) {
        this.mView = mView;
        mBusLineInfos = new ArrayList<>();
    }

    public void setKeyword(String mKeyword){
        this.mKeyword = mKeyword;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getBusLineInfo(mKeyword)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .doOnNext(new Action1<BusLineInfo>() {
                    @Override
                    public void call(BusLineInfo newsDetailBean) {

                    }
                })
                .subscribeOn(Schedulers.io())
                //.compose(mView.<BusStopInfo>bindToLife())
                .subscribe(new Subscriber<BusLineInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        Log.e("daibin",e.toString());
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(BusLineInfo busLineInfo) {
                        mView.loadData(busLineInfo);
                    }
                });
    }

    @Override
    public void getMoreData(List<String> params) {

    }
}
