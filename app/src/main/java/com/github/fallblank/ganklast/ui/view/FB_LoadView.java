package com.github.fallblank.ganklast.ui.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fallb on 2016/4/23.
 */
public interface FB_LoadView extends FB_BaseView {
    void show();

    void success();

    void error(Throwable e);

    void loadFinish(List<String> list);
}
