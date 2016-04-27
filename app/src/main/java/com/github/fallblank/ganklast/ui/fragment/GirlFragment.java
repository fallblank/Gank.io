package com.github.fallblank.ganklast.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.presenter.TypePresenter;
import com.github.fallblank.ganklast.ui.adapter.GirlAdapter;
import com.github.fallblank.ganklast.ui.view.FB_SwipeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fallb on 2016/4/26.
 */
public class GirlFragment extends Fragment implements FB_SwipeView {
    private static final String TAG = "GirlFragment";
    private ArrayList<Gank> mGirls;

    private RecyclerView mRecyclerView;
    private GirlAdapter mAdapter;
    private TypePresenter mPresenter;

    private String mType = "福利";
    private int mPagerSize = 8;
    private int mPagerIndex = 1;
    private ProgressBar mProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGirls = new ArrayList<>();
        mPresenter = new TypePresenter(getContext(), this);
        mPresenter.getTypeData(mType, mPagerSize, mPagerIndex);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        initRecycleView();
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        return view;
    }

    private void initRecycleView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new GirlAdapter(getActivity(), mGirls);
        mRecyclerView.setAdapter(mAdapter);
        //上拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isBottom = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING && isBottom) {
                    mPresenter.getTypeData(mType, mPagerSize, mPagerIndex);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isBottom = !ViewCompat.canScrollVertically(recyclerView, 1);
            }
        });
    }

    @Override
    public void showIndicator() {
        if (mProgressBar == null) return;
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIndicator() {
        mProgressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        }, 1500);
    }

    @Override
    public void finish(List<Gank> list) {
        mGirls.addAll(list);
    }

    @Override
    public void error(Throwable e) {
        Log.e(TAG, "error", e);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void complete() {
        mAdapter.notifyDataSetChanged();
        mPagerIndex += 1;
        mProgressBar.setVisibility(View.GONE);
    }
}
