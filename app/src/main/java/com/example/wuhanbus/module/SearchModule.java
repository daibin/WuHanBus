package com.example.wuhanbus.module;

import com.example.wuhanbus.activity.SearchActivity;
import com.example.wuhanbus.adapter.SearchAdapter;
import com.example.wuhanbus.presenter.IBasePresenter;
import com.example.wuhanbus.presenter.SearchPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    private final SearchActivity mView;

    public SearchModule(SearchActivity view) {
        this.mView = view;
    }

    @Provides
    public IBasePresenter providePresenter() {
        return new SearchPresenter(mView);
    }

    @Provides
    public SearchAdapter providerBusAdapter(){
        return new SearchAdapter(mView);
    }
}
