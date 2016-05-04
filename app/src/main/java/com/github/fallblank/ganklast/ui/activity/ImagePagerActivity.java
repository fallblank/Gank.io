package com.github.fallblank.ganklast.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.ui.adapter.ImagePagerAdapter;

import java.util.ArrayList;

/**
 * Created by fallb on 2016/5/4.
 */
public class ImagePagerActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_LISE = "imagelist";
    public static final String EXTRA_POSITION = "position";



    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        ArrayList<Gank> images = (ArrayList<Gank>) getIntent().getSerializableExtra(EXTRA_IMAGE_LISE);
        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), images, getApplicationContext());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(position, false);
    }
}
