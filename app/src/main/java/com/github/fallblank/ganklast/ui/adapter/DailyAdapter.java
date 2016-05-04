package com.github.fallblank.ganklast.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.ui.activity.BaseActivity;
import com.github.fallblank.ganklast.ui.activity.WebActivity;
import com.github.fallblank.ganklast.ui.fragment.ImageDialogFrament;
import com.github.fallblank.ganklast.util.StringStyleUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fallb on 2016/4/23.
 */
public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.BaseViewHolder> {

    private List<Gank> mGanks;
    private BaseActivity mActivity;
    private String mTimestamp;

    public enum ItemType {
        GIRL, CATEGORY, DESCRIPTION;
    }

    public DailyAdapter(Activity activity, List<Gank> data, String dateStr) {
        mGanks = data;
        mActivity = (BaseActivity) activity;
        mTimestamp = dateStr;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == ItemType.GIRL.ordinal()) {
            view = LayoutInflater.from(mActivity).inflate(R.layout.item_image_header, parent, false);
            return new ImageViewHoler(view);
        } else if (viewType == ItemType.CATEGORY.ordinal()) {
            view = LayoutInflater.from(mActivity).inflate(R.layout.item_categoty, parent, false);
            return new CategoryViewHolder(view);
        } else {
            view = LayoutInflater.from(mActivity).inflate(R.layout.item_gank, parent, false);
            return new DescViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Gank gank = mGanks.get(position);
        holder.bindData(gank);
    }

    @Override
    public int getItemViewType(int position) {
        Gank gank = mGanks.get(position);
        if (gank.type.equals("福利")) {
            return ItemType.GIRL.ordinal();
        } else if (gank.isHeader) {
            return ItemType.CATEGORY.ordinal();
        } else {
            return ItemType.DESCRIPTION.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mGanks.size();
    }

    /**
     * 如果是第一个就添加一个分类
     */
    abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindData(Gank gank);
    }

    /**
     * 图片条目
     */

    class ImageViewHoler extends BaseViewHolder {
        private final String TAG = "ImageViewHoler";
        ViewFlipper mViewFlipper;
        TextView mTextView;

        public ImageViewHoler(View itemView) {
            super(itemView);
            mViewFlipper = (ViewFlipper) itemView.findViewById(R.id.view_flipper);
            mTextView = (TextView) itemView.findViewById(R.id.timestamp);
        }

        @Override
        public void bindData(final Gank gank) {
            mTextView.setText(mTimestamp);
            final ImageView imageView = new ImageView(mActivity);
            imageView.setMaxWidth(mViewFlipper.getWidth());
            imageView.setMaxHeight(mViewFlipper.getHeight());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageDialogFrament frament = ImageDialogFrament.newInstance(gank.url);
                    frament.setStyle(android.support.v4.app.DialogFragment.STYLE_NORMAL, R.style.Dialog_Fullscreen);
                    frament.show(mActivity.getSupportFragmentManager(), "IMAGE");
                }
            });
            Picasso.with(mActivity)
                    .load(gank.url)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            mViewFlipper.addView(imageView);
                        }

                        @Override
                        public void onError() {
                            Log.e(TAG, "get image error");
                        }
                    });
        }
    }


    /**
     * 具体条目
     */
    class DescViewHolder extends BaseViewHolder {
        public TextView mTvDesc;

        public DescViewHolder(View itemView) {
            super(itemView);
            mTvDesc = (TextView) itemView.findViewById(R.id.description);
        }

        @Override
        public void bindData(final Gank gank) {
            mTvDesc.setText(StringStyleUtils.getGankInfoSequence(mTvDesc.getContext(), gank));
            mTvDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = WebActivity.newIntent(mActivity, gank.url, "test");
                    mActivity.startActivity(i);
                }
            });
        }
    }

    /**
     * 分类条目
     */

    class CategoryViewHolder extends BaseViewHolder {

        TextView mTvCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            mTvCategory = (TextView) itemView.findViewById(R.id.category);
        }

        @Override
        public void bindData(Gank gank) {
            mTvCategory.setText(gank.type);
        }
    }
}
