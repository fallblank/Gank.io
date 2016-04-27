package com.github.fallblank.ganklast.data;

import com.github.fallblank.ganklast.data.entity.Gank;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by fallb on 2016/4/23.
 */
public class Daily extends BaseData {
    public List<String> category;

    public Result results;

    public class Result{
        @SerializedName("Android") public List<Gank> androidList;
        @SerializedName("iOS") public List<Gank> iosList;
        @SerializedName("休息视频") public List<Gank> videoList;
        @SerializedName("福利") public List<Gank> girlList;
        @SerializedName("瞎推荐") public List<Gank> otherList;
        @SerializedName("前端") public List<Gank> htmlList;
    }

}
