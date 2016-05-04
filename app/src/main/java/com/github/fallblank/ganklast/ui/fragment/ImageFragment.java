package com.github.fallblank.ganklast.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.data.History;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by fallb on 2016/5/4.
 */
public class ImageFragment extends Fragment {

    private final static String ARG_URL = "url";
    private final static String ARG_HINT = "hint";

    public static ImageFragment newInstance(String url, String hint) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_URL, url);
        bundle.putString(ARG_HINT, hint);
        ImageFragment frament = new ImageFragment();
        frament.setArguments(bundle);
        return frament;
    }


    @Override
    public void onStart() {
        super.onStart();
        Picasso.with(getContext())
                .load(getArguments().getString(ARG_URL))
                .into(mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        mImageView.setImageResource(R.drawable.error);
                    }
                });
    }

    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private FloatingActionButton mFabSave;
    private TextView mTvHint;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mImageView = (ImageView) view.findViewById(R.id.image);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mFabSave = (FloatingActionButton) view.findViewById(R.id.fab_save);
        mFabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "还没实现", Toast.LENGTH_SHORT).show();
            }
        });
        mTvHint = (TextView) view.findViewById(R.id.hint);
        final String hint = getArguments().getString(ARG_HINT);
        mTvHint.setText(hint);
        return view;
    }
}