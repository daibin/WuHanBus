package com.example.wuhanbus.module;

import com.example.wuhanbus.activity.FavoriteActivity;
import com.example.wuhanbus.activity.MainActivity;
import com.example.wuhanbus.adapter.BusStopAdapter;
import com.example.wuhanbus.adapter.FavoriteAdapter;
import com.example.wuhanbus.presenter.BusStopPresenter;
import com.example.wuhanbus.presenter.FavoritePresenter;
import com.example.wuhanbus.presenter.IBasePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class FavoriteModule {

    private final FavoriteActivity mView;

    public FavoriteModule(FavoriteActivity view) {
        this.mView = view;
    }

    @Provides
    public IBasePresenter providePresenter() {
        return new FavoritePresenter(mView);
    }

    @Provides
    public FavoriteAdapter providerBusAdapter(){
        return new FavoriteAdapter(mView);
    }
}
