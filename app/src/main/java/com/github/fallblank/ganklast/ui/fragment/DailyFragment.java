package com.github.fallblank.ganklast.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.presenter.DailyPresenter;
import com.github.fallblank.ganklast.ui.adapter.DailyAdapter;
import com.github.fallblank.ganklast.ui.view.FB_Daily;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fallb on 2016/4/23.
 * todo:
 * 1.水纹效果没实现
 * 2.联动动画没实现
 * 3.图片点击放大动画没实现
 * 4.未处理点击事件
 */
public class DailyFragment extends Fragment implements FB_Daily {
    private static final String TAG = "DailyFragment";
    private static final String ARG_DATE = "DailyFragment.date";

    private List<Gank> mGanks = new ArrayList<>();

    public static DailyFragment newInstance(String date) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_DATE, date);
        DailyFragment fragment = new DailyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public DailyFragment() {
    }

    private RecyclerView mRecyclerView;
    private DailyAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String date = getArguments().getString(ARG_DATE);
        Log.i(TAG, "date:" + date);
        DailyPresenter presenter = new DailyPresenter(getContext(), this);
        presenter.getDailyData(date);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DailyAdapter(getActivity(), mGanks, getArguments().getString(ARG_DATE));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }


    @Override
    public void prepare() {

    }

    @Override
    public void finish(List<Gank> list) {
        String type = "";
        for (Gank gank : list) {
            if (gank.type.equals("福利")) {
                mGanks.add(0, gank);
            } else {
                if (!type.equals(gank.type)) {
                    Gank g = gank.clone();
                    g.isHeader = true;
                    mGanks.add(g);
                    type = g.type;
                }
                mGanks.add(gank);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(Throwable e) {
        Log.e(TAG, "get data errot", e);
    }

}
