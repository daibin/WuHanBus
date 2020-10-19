package com.example.wuhanbus.component;

import com.example.wuhanbus.activity.FavoriteActivity;
import com.example.wuhanbus.module.FavoriteModule;

import dagger.Component;

@Component(modules = FavoriteModule.class)
public interface FavoriteComponent {
    void inject(FavoriteActivity activity);
}
