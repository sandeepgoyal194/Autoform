package com.application.autoform.view.productview.seatcover.custom_layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.OldSeatCover;
import com.application.autoform.model.bean.Product;
import com.application.autoform.view.productview.seatcover.SeatCoverFullListActivity;

import java.util.List;

/**
 * Created by sandeep.g9 on 10/18/2016.
 */

public class AdvertisementBanner extends LinearLayout {
    private String mTitle;
    private String mType;
    private TextView mTextViewTitle;
    private Button mButtonMore;
    private AdvertisementBannerAdapter mAdvertisementBannerAdapter;
    private ViewPager mAdvertisementHolder;

    public AdvertisementBanner(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mAdvertisementBannerAdapter = new AdvertisementBannerAdapter(context);
        init();
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.adverisement_banner,
                0, 0);
        try {
            setmTitle(a.getString(R.styleable.adverisement_banner_title_banner));
            setmType(a.getString(R.styleable.adverisement_banner_type));
            mAdvertisementHolder.setAdapter(mAdvertisementBannerAdapter);
            mButtonMore.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, SeatCoverFullListActivity.class));
                }
            });
        } finally {
            a.recycle();
        }


    }


    public void setmSeatCovers(List<Product> mSeatCovers) {
        mAdvertisementBannerAdapter.setmSeatCovers(mSeatCovers);
    }

    public void addSeatCover(OldSeatCover cover) {

        mAdvertisementBannerAdapter.addSeatCover(cover);
    }

    public void removeSeatCover(OldSeatCover cover) {
        mAdvertisementBannerAdapter.removeSeatCover(cover);
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.advertisier_board, this);
        mTextViewTitle = (TextView) itemView.findViewById(R.id.txt_heading);
        mButtonMore = (Button) itemView.findViewById(R.id.btn_more);
        mAdvertisementHolder = (ViewPager) itemView.findViewById(R.id.advertisement_holder);
    }


    private void setmTitle() {
        mTextViewTitle.setText(mTitle);
    }


    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
        setmTitle();
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }


}
