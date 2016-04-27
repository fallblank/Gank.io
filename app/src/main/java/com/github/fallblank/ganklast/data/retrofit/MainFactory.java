package com.github.fallblank.ganklast.data.retrofit;

/**
 * Created by fallb on 2016/4/22.
 */
public class MainFactory {
    private static GankData mDateService;

    public static GankData getInstance() {
        if (mDateService == null) {
            synchronized (MainFactory.class) {
                if (mDateService == null) {
                    mDateService = new MainRetrofit().getService();
                }
            }
        }
        return mDateService;
    }
}
