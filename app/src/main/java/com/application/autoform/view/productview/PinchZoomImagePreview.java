package com.application.autoform.view.productview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.application.autoform.R;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.view.AutoFormActivity;
import com.application.autoform.view.TouchImageView;

public class PinchZoomImagePreview extends AutoFormActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinch_zoom_image);
        TouchImageView imageView = (TouchImageView)findViewById(R.id.imageView);
        setmToolbar();
        setmToolbarColor(Color.WHITE);
        setTitle("");
        new GlideImageLoaderImpl(this).loadImage(getIntent().getStringExtra("picture_url"), imageView, R.drawable.loading);

    }
    public void setmToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_black));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
