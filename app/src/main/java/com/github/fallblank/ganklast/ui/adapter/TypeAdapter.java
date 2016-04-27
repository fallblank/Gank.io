package com.github.fallblank.ganklast.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.entity.Gank;
import com.github.fallblank.ganklast.ui.activity.WebActivity;
import com.github.fallblank.ganklast.util.StringStyleUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fallb on 2016/4/24.
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ItemViewHolder> {

    private Context mAppContext;
    private List<Gank> mDataList;

    public TypeAdapter(Context context, List<Gank> list) {
        mAppContext = context;
        mDataList = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mAppContext).inflate(R.layout.item_gank, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bindData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

     class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvDesc;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTvDesc = (TextView) itemView.findViewById(R.id.description);
        }

        public void bindData(final Gank gank) {
            mTvDesc.setText(StringStyleUtils.getGankInfoSequence(mTvDesc.getContext(), gank));
            mTvDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = WebActivity.newIntent(mAppContext, gank.url, "test");
                    mAppContext.startActivity(i);
                }
            });
        }
    }
}
