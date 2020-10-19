package com.example.wuhanbus.view;

public interface ILoadDataView<T> extends IBaseView {

    /**
     * 加载数据
     * @param data 数据
     */
    void loadData(T data);

    /**
     * 加载更多
     * @param data 数据
     */
    /*void loadMoreData(T data);*/

    /**
     * 没有数据
     */
    /*void loadNoData();*/
}
