package com.application.autoform.networknew.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.application.autoform.networknew.WebServicesWrapper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by sandeep.g9 on 10/20/2016.
 */

public class GlideImageLoaderImpl implements ImageLoader {
    Context mContext;

    public GlideImageLoaderImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(WebServicesWrapper.IMAGE_URL + url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imageView);
    }

    public void loadImage(String url, ImageView imageView, int placeHolderID) {
        Glide.with(mContext)
                .load(WebServicesWrapper.IMAGE_URL + url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .placeholder(placeHolderID)
                .into(imageView);
    }
}
