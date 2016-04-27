package com.github.fallblank.ganklast.presenter;

import android.content.Context;
import android.util.Log;

import com.github.fallblank.ganklast.data.Daily;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.ui.view.FB_BaseView;
import com.github.fallblank.ganklast.ui.view.FB_Daily;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by fallb on 2016/4/23.
 */
public class DailyPresenter extends BasePresenter<FB_Daily> {

    private static final String TAG = "DailyPresenter";

    public DailyPresenter(Context context, FB_Daily view) {
        super(context, view);
    }

    public void getDailyData(String date) {
        mView.prepare();
        String[] array = date.split("-");
        Log.i(TAG, "" + array);
        assert array.length == 3;
        sDataCenter.getDaily(array[0], array[1], array[2])
                .subscribeOn(Schedulers.io())
                .map(new Func1<Daily, Daily.Result>() {
                    @Override
                    public Daily.Result call(Daily daily) {
                        return daily.results;
                    }
                })
                .map(new Func1<Daily.Result, List<Gank>>() {
                    @Override
                    public List<Gank> call(Daily.Result result) {
                        ArrayList<Gank> ganks = new ArrayList<Gank>();
                        addAll(ganks, result);
                        return ganks;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.error(e);
                    }

                    @Override
                    public void onNext(List<Gank> list) {
                        mView.finish(list);
                    }
                });
    }

    private void addAll(ArrayList<Gank> ganks, Daily.Result result) {
        if (result.girlList != null)
            ganks.addAll(result.girlList);
        if (result.androidList != null)
            ganks.addAll(result.androidList);
        if (result.iosList != null)
            ganks.addAll(result.iosList);
        if (result.htmlList != null)
            ganks.addAll(result.htmlList);
        if (result.otherList != null)
            ganks.addAll(result.otherList);
        if (result.videoList != null)
            ganks.addAll(result.videoList);
    }


}
