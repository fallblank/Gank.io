package com.github.fallblank.ganklast.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.presenter.TypePresenter;
import com.github.fallblank.ganklast.ui.adapter.TypeAdapter;
import com.github.fallblank.ganklast.ui.view.FB_SwipeView;
import com.github.fallblank.ganklast.util.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fallb on 2016/4/24.
 */
public class TypeFragment extends Fragment implements FB_SwipeView {
    private static final String TAG = "TypeFragment";
    private static final String ARG_TYPE = "type";

    public static TypeFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TYPE, type);
        TypeFragment fragment = new TypeFragment();
        fragment.setArguments(bundle);
        Log.d(TAG, "newInstance");
        return fragment;
    }


    public TypeFragment() {
    }

    private TypePresenter mPresenter;
    private int mPagerIndex = 1;
    private int mPagerSize = 8;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private TypeAdapter mAdapter;
    private List<Gank> mDataList = new ArrayList<>();
    private String mType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //获取数据
        mPresenter = new TypePresenter(getContext(), this);
        mType = getArguments().getString(ARG_TYPE);
        mPresenter.getTypeData(mType, mPagerSize, mPagerIndex);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "createview");
        View view = inflater.inflate(R.layout.swipe_list, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        //更新操作
        initRecycleView();
        return view;
    }

    /**
     * 上啦刷新
     */
    private void initRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new TypeAdapter(getContext(), mDataList);
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
    public void finish(List<Gank> list) {
        mDataList.addAll(list);
    }

    @Override
    public void error(Throwable e) {
        Log.e(TAG, "error", e);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void complete() {
        mPagerIndex += 1;
        mAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
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
        }, 3000);
    }
}
