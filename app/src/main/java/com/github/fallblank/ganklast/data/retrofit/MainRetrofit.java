package com.github.fallblank.ganklast.data.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by fallb on 2016/4/22.
 */
public class MainRetrofit {
    final GankData mService;

    private static final String HOST = "http://gank.io/api";

    final Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").serializeNulls().create();

    MainRetrofit() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(21, TimeUnit.SECONDS);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(HOST)
                .setConverter(new GsonConverter(mGson))
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .build();
        mService = restAdapter.create(GankData.class);
    }

    public GankData getService() {
        return mService;
    }
}
