package com.example.wuhanbus.api;

import com.example.wuhanbus.bean.BusLineInfo;
import com.example.wuhanbus.bean.BusStopInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface IBusStopInfoApi {

    @GET("line/027-{lineNo}-{direction}.do?Type=LineDetail")
    Observable<BusStopInfo> getBusStopInfo(@Path("lineNo") String lineNo, @Path("direction") int direction);

//    http://bus.wuhancloud.cn:9087/website//web/420100/search.do?keyword=7&type=line
    @GET("search.do?type=line")
    Observable<BusLineInfo> getBusLineInfo(@Query("keyword") String keyword);
}
