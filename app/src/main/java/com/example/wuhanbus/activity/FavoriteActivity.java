package com.example.wuhanbus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.wuhanbus.R;
import com.example.wuhanbus.adapter.FavoriteAdapter;
import com.example.wuhanbus.bean.BusStopInfo;
import com.example.wuhanbus.component.DaggerFavoriteComponent;

import com.example.wuhanbus.module.FavoriteModule;
import com.example.wuhanbus.view.ILoadDataView;
import com.example.wuhanbus.view.RecyclerViewHelper;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class FavoriteActivity extends BaseActivity implements ILoadDataView<BusStopInfo>{

    @BindView(R.id.recyclerView_favorite)
    RecyclerView mRecyclerView;

    /*@BindView(R.id.et_favorite_search)
    EditText mEditTextSearch;*/

    @Inject
    public FavoriteAdapter mAdapter;

    private List<String> mFavoriteInfos;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void initInjector() {
        DaggerFavoriteComponent.builder()
                .favoriteModule(new FavoriteModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        mFavoriteInfos = new ArrayList<>();
        RecyclerViewHelper.initRecyclerViewV(this,mRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        freshFavoriteInfo();
        mAdapter.setOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                freshFavoriteInfo();
                String favoriteInfo = mFavoriteInfos.get(position);
                startActivity(favoriteInfo,FavoriteActivity.this,MainActivity.class);
            }
        });
    }

    private void startActivity(String extra,Context context,Class<?> cls){
        Intent intent = new Intent();
        if(null != extra) {
            intent.putExtra(LINE_NAME, extra);
        }
        intent.setClass(context,cls);
        startActivity(intent);
    }

    private void  freshFavoriteInfo(){
        //Toast.makeText(FavoriteActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
        mFavoriteInfos.clear();
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.FAVORITE_LINE, Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPreferences.getAll();
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String val = (String) entry.getValue();
            mFavoriteInfos.add(val);
        }
        if(null != mFavoriteInfos && !mFavoriteInfos.isEmpty()){
            Collections.sort(mFavoriteInfos, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if(o1.compareToIgnoreCase(o2)<0){
                        return -1;
                    }
                    return 1;
                }
            });
            mPresenter.getMoreData(mFavoriteInfos);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        handler.sendEmptyMessage(ONRESUME);
        if(isFreshing){
            findViewById(R.id.btn_favorite_refresh).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else{
            findViewById(R.id.btn_favorite_refresh).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(UPDATE);
        isFreshing = false;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case UPDATE:
                    freshFavoriteInfo();
                    handler.sendEmptyMessageDelayed(UPDATE,DELAY_MILLIS_10S);
                    break;
                case ONRESUME:
                    freshFavoriteInfo();
                    break;
            }
        }
    };

    @OnClick({R.id.btn_favorite_refresh,R.id.btn_favorite_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_favorite_search:
                /*String lineName = mEditTextSearch.getText().toString().trim();
                if(TextUtils.isEmpty(lineName)){
                    return;
                }
                startActivity(lineName+"_1");
                mEditTextSearch.setText("");*/
                startActivity(null,FavoriteActivity.this,SearchActivity.class);
                break;
            case R.id.btn_favorite_refresh:
                if(!isFreshing) {
                    findViewById(R.id.btn_favorite_refresh).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    if(null != mFavoriteInfos && !mFavoriteInfos.isEmpty()){
                        freshFavoriteInfo();
                    }
                    if (handler.hasMessages(UPDATE)) {
                        handler.removeMessages(UPDATE);
                    }
                    handler.sendEmptyMessageDelayed(UPDATE, DELAY_MILLIS_10S);
                    isFreshing = true;
                }else{
                    findViewById(R.id.btn_favorite_refresh).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    if (handler.hasMessages(UPDATE)) {
                        handler.removeMessages(UPDATE);
                    }
                    isFreshing = false;
                }
                break;
        }
    }

    @Override
    public void loadData(BusStopInfo data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(List<BusStopInfo> data) {
        mAdapter.setData(data);
    }

    @Override
    public void showNetError() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }
}
