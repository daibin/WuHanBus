package com.example.wuhanbus.module;

import com.example.wuhanbus.activity.MainActivity;
import com.example.wuhanbus.adapter.BusStopAdapter;
import com.example.wuhanbus.presenter.BusStopPresenter;
import com.example.wuhanbus.presenter.IBasePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class BusStopModule {

    private final MainActivity mView;

    public BusStopModule(MainActivity view) {
        this.mView = view;
    }

    @Provides
    public IBasePresenter providePresenter() {
        return new BusStopPresenter(mView);
    }

    @Provides
    public BusStopAdapter providerBusAdapter(){
        return new BusStopAdapter(mView);
    }
}
