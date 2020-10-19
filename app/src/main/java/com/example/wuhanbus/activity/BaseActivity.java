package com.example.wuhanbus.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wuhanbus.presenter.IBasePresenter;
import com.example.wuhanbus.service.RetrofitService;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity {

    protected static final String LINE_NAME = "line_name";
    protected static final int UPDATE = 1;
    protected static final int ONRESUME = 2;
    protected boolean isFreshing = false;
    protected static final int DELAY_MILLIS_5S = 3*1000;
    protected static final int DELAY_MILLIS_10S = 3*1000;

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        RetrofitService.init();
        initInjector();
        initViews();
    }

    protected abstract int attachLayoutRes();

    protected abstract void initInjector();

    protected abstract void initViews();
}
