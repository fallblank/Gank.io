package com.github.fallblank.ganklast.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.fallblank.ganklast.R;

/**
 * Created by fallb on 2016/4/23.
 * this base activity is just inflate a toolbar
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final static String TAG = "BaseActivity";
    protected Toolbar mToolbar;
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar!=null){
            setSupportActionBar(mToolbar);
        }else {
            Log.d(TAG,"you need to include a toolbar into your layout");
        }
    }
}
