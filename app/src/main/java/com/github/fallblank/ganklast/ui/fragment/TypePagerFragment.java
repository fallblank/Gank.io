package com.github.fallblank.ganklast.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.ui.adapter.TypePagerAdapter;

/**
 * Created by fallb on 2016/4/24.
 */
public class TypePagerFragment extends Fragment {
    private static final String TAG = "TypePagerFragment";

    private ViewPager mPager;
    private TypePagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type_pager, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mAdapter = new TypePagerAdapter(getFragmentManager(), getContext());
        mPager.setAdapter(mAdapter);
        return view;
    }
}
