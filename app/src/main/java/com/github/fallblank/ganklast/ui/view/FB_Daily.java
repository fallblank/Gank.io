package com.github.fallblank.ganklast.ui.view;

import com.github.fallblank.ganklast.data.entity.Gank;

import java.util.List;

/**
 * Created by fallb on 2016/4/23.
 */
public interface FB_Daily extends FB_BaseView {
    void prepare();

    void finish(List<Gank> list);

    void error(Throwable e);

}
