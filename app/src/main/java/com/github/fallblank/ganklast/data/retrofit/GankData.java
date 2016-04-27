package com.github.fallblank.ganklast.data.retrofit;


import com.github.fallblank.ganklast.data.Daily;
import com.github.fallblank.ganklast.data.History;
import com.github.fallblank.ganklast.data.TypeData;
import com.github.fallblank.ganklast.util.Type;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by fallb on 2016/4/22.
 */
public interface GankData {
    /**
     * 获取发布日期数据
     *
     * @return
     */
    @GET("/day/history")
    Observable<History> getHistory();

    /**
     * http://gank.io/api/day/2016/04/22
     */
    @GET("/day/{year}/{month}/{day}")
    Observable<Daily> getDaily(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    /**
     * http://gank.io/api/data/数据类型/请求个数/第几页
     */

    @GET("/data/{type}/{pagersize}/{pagerindex}")
    Observable<TypeData> getTypeData(@Path("type") String type, @Path("pagersize") int size, @Path("pagerindex") int index);

}
