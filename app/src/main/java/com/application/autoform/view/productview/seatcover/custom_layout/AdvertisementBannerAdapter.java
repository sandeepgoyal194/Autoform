package com.application.autoform.view.productview.seatcover.custom_layout;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.OldSeatCover;
import com.application.autoform.model.bean.Product;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;
import com.application.autoform.view.productview.seatcover.SeatCoverDetailActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sandeep.g9 on 10/20/2016.
 */

public class AdvertisementBannerAdapter extends PagerAdapter {
    static ImageLoader mImageLoader;
    Context mContext;
    List<Product> mSeatCovers = new ArrayList<>();

    public AdvertisementBannerAdapter(Context mContext) {
        this.mContext = mContext;
        mImageLoader = new GlideImageLoaderImpl(mContext);
    }

    @Override
    public int getCount() {
        return mSeatCovers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(ViewHolder.getLayoutID(), container, false);
        ViewHolder holder = new ViewHolder(itemView);
        holder.setContent(mSeatCovers.get(position));
        container.addView(itemView);
        return itemView;
    }

    public List<Product> getmSeatCovers() {
        return mSeatCovers;
    }

    public void setmSeatCovers(List<Product> mSeatCovers) {
        this.mSeatCovers = mSeatCovers;
        notifyDataSetChanged();
    }

    public void addSeatCover(OldSeatCover cover) {
        mSeatCovers.add(cover);
        notifyDataSetChanged();
    }

    public void removeSeatCover(OldSeatCover cover) {
        mSeatCovers.remove(cover);
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    static class ViewHolder {
        ImageView coverImage;
        ImageView majorImage;
        ImageView minorImage;
        TextView majorColor;
        TextView minorColor;
        TextView price;
        TextView desc;
        Product cover;

        ViewHolder(View itemView) {
            coverImage = (ImageView) itemView.findViewById(R.id.imageView);
            majorImage = (ImageView) itemView.findViewById(R.id.img_major_color);
            minorImage = (ImageView) itemView.findViewById(R.id.img_minor_color);
            majorColor = (TextView) itemView.findViewById(R.id.txt_major_color);
            minorColor = (TextView) itemView.findViewById(R.id.txt_minor_color);
            //price = (TextView) itemView.findViewById(R.id.txt_price);
            desc = (TextView) itemView.findViewById(R.id.brand_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), SeatCoverDetailActivity.class);
                    //TODO: make static final this string DESIGN
                    i.putExtra("DESIGN", cover.getDesignID());
                    v.getContext().startActivity(i);
                }
            });
        }

        static int getLayoutID() {
            return R.layout.advertisement_banner_seat_design;
        }

        void setContent(Product cover) {
            this.cover = cover;
            setImageOrSetGone(cover.getMajorMinorList().get(0).getMajorColorPath(), majorImage);
            setImageOrSetGone(cover.getMajorMinorList().get(0).getMinorColorPath(), minorImage);
            mImageLoader.loadImage(cover.getMajorMinorList().get(0).getDesignImage(), coverImage, R.drawable.loading);
            majorColor.setText(cover.getMajorMinorList().get(0).getMajorColor());
            minorColor.setText(cover.getMajorMinorList().get(0).getMinorColor());
            desc.setText(cover.getDesignName());
        }

        void setImageOrSetGone(String path, ImageView image) {
            if (path != null && !path.trim().isEmpty()) {
                mImageLoader.loadImage(path, image, R.drawable.loading);
            } else {
                image.setVisibility(View.GONE);
            }
        }
    }

}
