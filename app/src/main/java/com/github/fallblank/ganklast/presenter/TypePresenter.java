package com.github.fallblank.ganklast.presenter;

import android.content.Context;

import com.github.fallblank.ganklast.data.TypeData;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.ui.view.FB_SwipeView;
import com.github.fallblank.ganklast.util.Type;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by fallb on 2016/4/24.
 */
public class TypePresenter extends BasePresenter<FB_SwipeView> {
    public TypePresenter(Context context, FB_SwipeView view) {
        super(context, view);
    }

    public void getTypeData(String type, int size, int pager) {
        mView.showIndicator();
        sDataCenter.getTypeData(type, size, pager)
                .subscribeOn(Schedulers.io())
                .map(new Func1<TypeData, List<Gank>>() {
                    @Override
                    public List<Gank> call(TypeData typeData) {
                        return typeData.results;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                        mView.hideIndicator();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.error(e);
                        mView.hideIndicator();
                    }

                    @Override
                    public void onNext(List<Gank> list) {
                        mView.finish(list);
                    }
                });

    }
}
