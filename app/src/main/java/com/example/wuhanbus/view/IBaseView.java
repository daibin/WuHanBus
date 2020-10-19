package com.example.wuhanbus.view;

import com.example.wuhanbus.bean.BusStopInfo;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.List;

public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading(List<BusStopInfo> infos);

    /**
     * 显示网络错误，modify 对网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    void showNetError();

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 完成刷新, 新增控制刷新
     */
    /*void finishRefresh();*/
}
