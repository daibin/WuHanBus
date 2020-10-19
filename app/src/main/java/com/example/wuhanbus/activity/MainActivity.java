package com.example.wuhanbus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuhanbus.R;
import com.example.wuhanbus.adapter.BusStopAdapter;
import com.example.wuhanbus.bean.BusStopInfo;
import com.example.wuhanbus.component.DaggerBusStopComponent;
import com.example.wuhanbus.module.BusStopModule;
import com.example.wuhanbus.presenter.BusStopPresenter;
import com.example.wuhanbus.view.ILoadDataView;
import com.example.wuhanbus.view.RecyclerViewHelper;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements ILoadDataView<BusStopInfo> {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.et_bus_line_num)
    EditText mEtBusLineNum;

    private int mOrientation=1;
    private String mBusLineNum;
    public static final String FAVORITE_LINE = "favoriteline";
    private int mChoicePosition = -1;
    private SharedPreferences mSp;

    @BindView(R.id.line_name)
    TextView mLineName;

    @BindView(R.id.start_end_stop_name)
    TextView mBusStart2EndStopName;

    @BindView(R.id.service_time)
    TextView mServiceTime;

    @BindView(R.id.price)
    TextView mPrice;

    @Inject
    public BusStopAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSp = getSharedPreferences(FAVORITE_LINE, Context.MODE_PRIVATE);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case UPDATE:
                    getData();
                    handler.sendEmptyMessageDelayed(UPDATE,DELAY_MILLIS_5S);
                    break;
                case ONRESUME:
                    getData();
                    break;
            }
        }
    };

    private void getData() {
        //Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
        mPresenter.getData(true);
    }

    @Override
    protected void initViews(){
        Intent intent = getIntent();
        String[] lineInfos = intent.getStringExtra(LINE_NAME).split("_");
        mBusLineNum = lineInfos[0];
        mOrientation = Integer.parseInt(lineInfos[1]);
        if(3 == lineInfos.length) {
            setChoicePosition(Integer.parseInt(lineInfos[2]));
        }
        ((BusStopPresenter) mPresenter).setLineDirection(mBusLineNum,mOrientation);
        mPresenter.getData(true);
        RecyclerViewHelper.initRecyclerViewH(this,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BusStopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(0 == position){
                    Toast.makeText(MainActivity.this,"无需收藏",Toast.LENGTH_SHORT).show();
                }
                mChoicePosition = position;
                setChoicePosition(mChoicePosition);
                String favoriteStopLine = mBusLineNum+"_"+mOrientation+"_"+mChoicePosition;
                String hasFavoriteLine = mSp.getString(favoriteStopLine,"");
                if(!hasFavoriteLine.isEmpty()) {
                    findViewById(R.id.btn_favorite).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Toast.makeText(MainActivity.this,"已收藏",Toast.LENGTH_SHORT).show();
                }else{
                    findViewById(R.id.btn_favorite).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        DaggerBusStopComponent.builder()
                .busStopModule(new BusStopModule(this))
                .build()
                .inject(this);
    }

    @OnClick({R.id.btn_search,R.id.btn_reverse,R.id.btn_refresh,R.id.btn_favorite})
    public void onClick(View view){
        SharedPreferences.Editor mEditor;
        switch (view.getId()){
            case R.id.btn_refresh:
                if(!isFreshing) {
                    findViewById(R.id.btn_refresh).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mPresenter.getData(true);
                    //Toast.makeText(MainActivity.this,"刷新中",Toast.LENGTH_SHORT).show();
                    if (handler.hasMessages(UPDATE)) {
                        handler.removeMessages(UPDATE);
                    }
                    handler.sendEmptyMessageDelayed(UPDATE, DELAY_MILLIS_5S);
                    isFreshing = true;
                }else{
                    //mChoicePosition = -1;
                    findViewById(R.id.btn_refresh).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    if (handler.hasMessages(UPDATE)) {
                        handler.removeMessages(UPDATE);
                    }
                    isFreshing = false;
                }
                break;
            case R.id.btn_reverse:
                mChoicePosition = -1;
                findViewById(R.id.btn_favorite).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                if(TextUtils.isEmpty(mBusLineNum)){
                    mBusLineNum = "536";
                }
                mOrientation=mOrientation==1?0:1;
                ((BusStopPresenter) mPresenter).setLineDirection(mBusLineNum,mOrientation);
                setChoicePosition(-1);
                break;
            case R.id.btn_search:
                String newSearchLine = mEtBusLineNum.getText().toString().trim();
                mBusLineNum = TextUtils.isEmpty(newSearchLine) ? mBusLineNum : newSearchLine;
                ((BusStopPresenter) mPresenter).setLineDirection(mBusLineNum,mOrientation);
                setChoicePosition(-1);
                break;
            case R.id.btn_favorite:
                if(mChoicePosition <= 0){
                    Toast.makeText(MainActivity.this,"请选择站点再收藏！",Toast.LENGTH_SHORT).show();
                    return;
                }
                String favoriteStopLine = mBusLineNum+"_"+mOrientation+"_"+mChoicePosition;
                String hasFavoriteLine = mSp.getString(favoriteStopLine,"");
                if(hasFavoriteLine.isEmpty()) {
                    mEditor = mSp.edit();
                    mEditor.putString(favoriteStopLine,favoriteStopLine);
                    mEditor.apply();
                    findViewById(R.id.btn_favorite).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Toast.makeText(MainActivity.this,"已收藏",Toast.LENGTH_SHORT).show();
                }else{
                    mEditor = mSp.edit();
                    mEditor.remove(favoriteStopLine);
                    mEditor.apply();
                    findViewById(R.id.btn_favorite).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(MainActivity.this,"取消收藏",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setChoicePosition(int position){
        mPresenter.getData(true);
        mAdapter.setChoicePosition(position);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(UPDATE);
        isFreshing = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(UPDATE);
        isFreshing = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        handler.sendEmptyMessage(ONRESUME);
        if(isFreshing){
            findViewById(R.id.btn_refresh).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else{
            findViewById(R.id.btn_refresh).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void loadData(BusStopInfo data) {
        mAdapter.setData(data.getData());
        mLineName.setText(data.getData().getLineName()+"路");
        mBusStart2EndStopName.setText(data.getData().getStartStopName()+"->"+data.getData().getEndStopName());
        mServiceTime.setText("运行时间 "+data.getData().getFirstTime()+"-"+data.getData().getLastTime());
        mPrice.setText("票价 约"+data.getData().getPrice());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(List infos) {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }
}
