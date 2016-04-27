package com.github.fallblank.ganklast.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.ui.adapter.DialyPagerAdapter;

import java.util.ArrayList;

/**
 * Created by fallb on 2016/4/23.
 */
public class DailyPagerFragment extends Fragment {
    private static final String TAG = "DailyPagerFragment";
    private static final String ARG_HISTORY = "DailyPagerFragment.history";

    /**
     * use a list to create fragment.the list contains a set of string standing for published data
     *
     * @param list
     * @return
     */
    public static DailyPagerFragment newInstance(ArrayList<String> list) {
        DailyPagerFragment fragment = new DailyPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ARG_HISTORY, list);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * deliberately remain,for other purpose in the future
     */
    public DailyPagerFragment() {
    }


    private ViewPager mPager;
    private PagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //remain to do:there should recover the state

        View view = inflater.inflate(R.layout.fragment_daily_pager, container, false);
        mPager = (ViewPager) view.findViewById(R.id.viewpager);
        ArrayList<String> history = getArguments().getStringArrayList(ARG_HISTORY);
        mAdapter = new DialyPagerAdapter(getFragmentManager(), history);
        mPager.setAdapter(mAdapter);
        return view;
    }
}
