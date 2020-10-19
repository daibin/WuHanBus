package com.example.wuhanbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.SearchView;

import com.example.wuhanbus.R;
import com.example.wuhanbus.adapter.SearchAdapter;
import com.example.wuhanbus.bean.BusLineInfo;
import com.example.wuhanbus.bean.BusStopInfo;
import com.example.wuhanbus.component.DaggerSearchComponent;
import com.example.wuhanbus.module.SearchModule;
import com.example.wuhanbus.presenter.SearchPresenter;
import com.example.wuhanbus.view.ILoadLinesInfoDataView;
import com.example.wuhanbus.view.RecyclerViewHelper;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;

public class SearchActivity extends BaseActivity implements ILoadLinesInfoDataView<BusLineInfo> {
    @BindView(R.id.searchView)
    SearchView mSearchView;

    @BindView(R.id.rv_bus_line_info)
    RecyclerView mRecyclerView;

    @Inject
    public SearchAdapter mAdapter;

    private List<BusLineInfo.LinesBean> mData;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            //当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }

            //当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText){
                if(!TextUtils.isEmpty(newText)){
                    ((SearchPresenter)mPresenter).setKeyword(newText);
                    mPresenter.getData(true);
                }else{
                    /*mRecyclerView.clear*/
                }
                return false;
            }
        });
    }

    @Override
    protected void initInjector() {
        DaggerSearchComponent.builder()
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        RecyclerViewHelper.initRecyclerViewV(this,mRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String extra = mData.get(position).getLineNo()+"_"+mData.get(position).getDirection();
                Intent intent = new Intent();
                intent.putExtra(LINE_NAME, extra);
                intent.setClass(SearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadData(BusLineInfo data) {
        mData = data.getData().getLines();
        mAdapter.setData(data.getData().getLines());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(List<BusStopInfo> infos) {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }
}
