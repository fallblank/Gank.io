package com.github.fallblank.ganklast.ui.view;

import com.github.fallblank.ganklast.data.entity.Gank;

import java.util.List;

/**
 * Created by fallb on 2016/4/24.
 */
public interface FB_SwipeView extends FB_BaseView {
    void showIndicator();

    void hideIndicator();

    void finish(List<Gank> list);

    void error(Throwable e);

    void complete();
}
