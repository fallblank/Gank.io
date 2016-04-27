package com.github.fallblank.ganklast.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.github.fallblank.ganklast.ui.fragment.DailyFragment;

import java.util.ArrayList;

/**
 * Created by fallb on 2016/4/22.
 */
public class DialyPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> mPublishTimeTable;

    public DialyPagerAdapter(FragmentManager fm, ArrayList<String> history) {
        super(fm);
        mPublishTimeTable = history;
    }

    @Override
    public Fragment getItem(int position) {
        return DailyFragment.newInstance(mPublishTimeTable.get(position));
    }

    @Override
    public int getCount() {
        return mPublishTimeTable.size();
    }
}
