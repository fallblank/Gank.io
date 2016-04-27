package com.github.fallblank.ganklast.presenter;

import android.content.Context;

import com.github.fallblank.ganklast.data.retrofit.GankData;
import com.github.fallblank.ganklast.data.retrofit.MainFactory;
import com.github.fallblank.ganklast.ui.view.FB_BaseView;

/**
 * Created by fallb on 2016/4/23.
 */
public class BasePresenter<V extends FB_BaseView> {
    protected Context mAppContext;

    protected V mView;

    public static final GankData sDataCenter = MainFactory.getInstance();

    public BasePresenter(Context context, V view) {
        mAppContext = context;
        mView = view;
    }
}
