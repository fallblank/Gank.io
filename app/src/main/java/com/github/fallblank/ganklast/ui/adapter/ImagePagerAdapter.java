package com.github.fallblank.ganklast.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.ui.fragment.ImageFragment;

import java.util.ArrayList;

/**
 * Created by fallb on 2016/5/4.
 */
public class ImagePagerAdapter extends FragmentPagerAdapter {
    private Context mAppContext;
    private ArrayList<Gank> mImageList;

    public ImagePagerAdapter(FragmentManager fm, ArrayList<Gank> list, Context context) {
        super(fm);
        mAppContext = context;
        mImageList = list;
    }


    @Override
    public Fragment getItem(int position) {
        ImageFragment fragment = ImageFragment.newInstance(mImageList.get(position).url, (position + 1) + "/" + getCount());
        return fragment;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }
}
