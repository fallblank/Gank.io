package com.github.fallblank.ganklast.presenter;

import android.content.Context;

import com.github.fallblank.ganklast.data.History;
import com.github.fallblank.ganklast.ui.view.FB_BaseView;
import com.github.fallblank.ganklast.ui.view.FB_LoadView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by fallb on 2016/4/23.
 */
public class LoadHistoryPresenter extends BasePresenter<FB_LoadView> {
    public LoadHistoryPresenter(Context context, FB_LoadView view) {
        super(context, view);
    }

    public void getHistory() {
        mView.show();
        sDataCenter.getHistory()
                .subscribeOn(Schedulers.io())
                .map(new Func1<History, List<String>>() {
                    @Override
                    public List<String> call(History history) {
                        return history.results;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        mView.success();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.error(e);
                    }

                    @Override
                    public void onNext(List<String> list) {
                        mView.loadFinish(list);
                    }
                });
    }

}
