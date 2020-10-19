package com.example.wuhanbus.component;

import com.example.wuhanbus.activity.MainActivity;
import com.example.wuhanbus.module.BusStopModule;

import dagger.Component;

@Component(modules = BusStopModule.class)
public interface BusStopComponent {
    void inject(MainActivity activity);
}
