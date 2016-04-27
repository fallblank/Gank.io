package com.github.fallblank.ganklast.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.fallblank.ganklast.ui.fragment.TypeFragment;
import com.github.fallblank.ganklast.util.Type;

/**
 * Created by fallb on 2016/4/24.
 */
public class TypePagerAdapter extends FragmentPagerAdapter {
    private Context mAppContext;
    private String[] types = {"Android", "iOS", "前端", "拓展资源"};
    private Fragment[] mFragments = new Fragment[types.length];

    public TypePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mAppContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments[position];
        if (fragment == null) {
            fragment = TypeFragment.newInstance(types[position]);
            mFragments[position] = fragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return types.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return types[position];
    }
}
