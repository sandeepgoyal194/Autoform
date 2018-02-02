package com.application.autoform.view.cars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.CarBrand;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 10/25/2016.
 */

public class CarBrandListAdapter extends RecyclerView.Adapter<CarBrandListAdapter.CarBrandHolder> {
    Context context;
    List<CarBrand> mCarBrands = new ArrayList<>();
    ImageLoader mImageLoader = null;
    CarBrandListFragment.OnBrandSelected mListener;

    public CarBrandListAdapter(Context context) {
        this.context = context;
        mImageLoader = new GlideImageLoaderImpl(context);
    }

    public CarBrandListAdapter(Context context, CarBrandListFragment.OnBrandSelected listener) {
        this(context);
        mListener = listener;
    }

    @Override
    public CarBrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_brand_item, null);
        return new CarBrandHolder(v);
    }

    @Override
    public void onBindViewHolder(CarBrandHolder holder, int position) {
        holder.setContent(mCarBrands.get(position));
    }

    @Override
    public int getItemCount() {
        if (mCarBrands != null)
            return mCarBrands.size();
        else
            return -1;
    }

    public void setCarBrands(List<CarBrand> mSeatCovers) {

        this.mCarBrands = mSeatCovers;
        notifyDataSetChanged();
    }

    public void addBrand(CarBrand brand) {
        mCarBrands.add(brand);
        notifyDataSetChanged();
    }

    public void removeCarBrand(CarBrand brand) {
        mCarBrands.remove(brand);
        notifyDataSetChanged();
    }

    class CarBrandHolder extends RecyclerView.ViewHolder {
        ImageView brandImage;
        TextView brandName;

        // TextView amount;
        // Button btnCheckout;
        public CarBrandHolder(View itemView) {
            super(itemView);
            // amount = (TextView) itemView.findViewById(R.id.amount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.setBrand(brandName.getText().toString());
                    }
                }
            });
            brandName = (TextView) itemView.findViewById(R.id.brand_name);
            brandImage = (ImageView) itemView.findViewById(R.id.brand_image);
            //  btnCheckout = (Button) itemView.findViewById(R.id.btn_checkout);
        }

        void setContent(CarBrand brand) {
            brandName.setText(brand.getMake());
            mImageLoader.loadImage(brand.getImagePath(), brandImage, R.drawable.loading);
        }
    }
}
