package com.example.wuhanbus.component;

import com.example.wuhanbus.activity.SearchActivity;
import com.example.wuhanbus.module.SearchModule;

import dagger.Component;

@Component(modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity activity);
}
