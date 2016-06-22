package com.github.fallblank.ganklast.ui.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.util.DateFormatUtils;
import com.github.fallblank.ganklast.util.Downloader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;

/**
 * Created by fallb on 2016/4/27.
 */
public class ImageDialogFrament extends DialogFragment {
    private final static String TAG_URL = "url";

    public static ImageDialogFrament newInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG_URL, url);
        ImageDialogFrament frament = new ImageDialogFrament();
        frament.setArguments(bundle);
        return frament;
    }


    @Override
    public void onStart() {
        super.onStart();
        Picasso.with(getContext())
                .load(getArguments().getString(TAG_URL))
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_dialog, container, false);
        mImageView = (ImageView) view.findViewById(R.id.image);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mFabSave = (FloatingActionButton) view.findViewById(R.id.fab_save);
        mFabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File root = Environment.getExternalStorageDirectory();
                File file = new File(root, "/GankIo/Image/"+ DateFormatUtils.getTimestamp()+".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                if (!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        Toast.makeText(getContext(), R.string.download_failed, Toast.LENGTH_SHORT);
                        return;
                    }
                }
                Downloader.download(getContext(),getArguments().getString(TAG_URL),file);
            }
        });
        return view;
    }
}
