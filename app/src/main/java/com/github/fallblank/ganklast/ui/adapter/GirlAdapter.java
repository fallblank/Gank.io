package com.github.fallblank.ganklast.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.ui.activity.BaseActivity;
import com.github.fallblank.ganklast.ui.activity.ImagePagerActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by fallb on 2016/4/26.
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder> {

    private BaseActivity mActivity;
    private ArrayList<Gank> mGirls;

    public GirlAdapter(Activity activity, ArrayList<Gank> data) {
        mActivity = (BaseActivity) activity;
        mGirls = data;
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_image, parent, false);
        return new GirlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GirlViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mGirls.size();
    }

    class GirlViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "GirlViewHolder";
        private ImageView mIvGirl;

        public GirlViewHolder(View itemView) {
            super(itemView);
            mIvGirl = (ImageView) itemView.findViewById(R.id.image);
        }

        public void bindData(final int position) {
            mIvGirl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mActivity, ImagePagerActivity.class);
                    i.putExtra(ImagePagerActivity.EXTRA_IMAGE_LISE, mGirls);
                    i.putExtra(ImagePagerActivity.EXTRA_POSITION, position);
                    mActivity.startActivity(i);
                }
            });
            Gank gank = mGirls.get(position);
            Picasso.with(mActivity)
                    .load(gank.url)
                    .into(mIvGirl, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                            Log.e(TAG, "get image error");
                        }
                    });
        }
    }
}
