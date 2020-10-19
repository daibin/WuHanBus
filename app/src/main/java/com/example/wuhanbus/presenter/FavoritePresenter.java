package com.example.wuhanbus.presenter;

import android.util.Log;

import com.example.wuhanbus.bean.BusStopInfo;
import com.example.wuhanbus.service.RetrofitService;
import com.example.wuhanbus.view.ILoadDataView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

public class FavoritePresenter implements IBasePresenter{
    private final ILoadDataView mView;

    public FavoritePresenter(ILoadDataView mView) {
        this.mView = mView;
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData(final List<String> params) {
        Observable.from(params).concatMap(new Func1<String, Observable<Observable<BusStopInfo>>>() {
            @Override
            public Observable<Observable<BusStopInfo>> call(String s) {
                String[] info = s.split("_");
                return Observable.just(RetrofitService.getBusStopInfo(info[0],Integer.parseInt(info[1])).subscribeOn(Schedulers.io()));
            }
        }).toList().subscribe(new Subscriber<List<Observable<BusStopInfo>>>() {
            @Override
            public void onCompleted() {
                Log.d("litao", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("litao", e.getMessage());
            }

            @Override
            public void onNext(List<Observable<BusStopInfo>> observables) {
                Log.d("litao", observables.size() + "");
                Observable.zip(observables, new FuncN<List<BusStopInfo>>() {
                    @Override
                    public List<BusStopInfo> call(Object... args) {
                        Log.d("litao", "call" + args.length);
                        List<BusStopInfo> list = new ArrayList<>(args.length);
                        for(int i=0;i<args.length;i++){
                            ((BusStopInfo)args[i]).getData().setCurrentStopId(Integer.parseInt(params.get(i).split("_")[2]));
                        }
                        for (Object arg : args) {
                            list.add((BusStopInfo) arg);
                        }
                        return list;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BusStopInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<BusStopInfo> busStopInfos) {
                        Log.d("litao", "onNext: "+busStopInfos.size());
                        mView.hideLoading(busStopInfos);
                    }
                });
            }
        });
        /*Observable.from(params)
                .flatMap(new Func1<String, Observable<BusStopInfo>>() {
                    @Override
                    public Observable<BusStopInfo> call(String s) {
                        mFavoriteLineInfo = s.split("-");
                        return RetrofitService.getBusStopInfo(mFavoriteLineInfo[0],Integer.parseInt(mFavoriteLineInfo[1]))
                                .doOnSubscribe(new Action0() {
                                    @Override
                                    public void call() {
                                        mView.showLoading();
                                    }
                                })
                                .doOnNext(new Action1<BusStopInfo>() {
                                    @Override
                                    public void call(BusStopInfo busStopInfo) {
                                        busStopInfo.getData().setCurrentStopId(Integer.parseInt(mFavoriteLineInfo[2]));
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<BusStopInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                mBusStopInfos.clear();
            }

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
                //mView.loadData(busStopInfo);
                if(size > mBusStopInfos.size()){
                    mBusStopInfos.add(busStopInfo);
                }
                Log.e("daibin","busStopInfo: "+busStopInfo.getData().toString());

            }
        });*/
    }
}
