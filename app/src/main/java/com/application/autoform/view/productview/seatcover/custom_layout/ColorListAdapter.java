package com.application.autoform.view.productview.seatcover.custom_layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.Color;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 11/3/2016.
 */

public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.ColorImageHolder> {
    List<Color> mColorList = new ArrayList<>();
    int mCurrentSelectionPosition;
    ImageLoader mImageLoader;
    Context mContext;

    public ColorListAdapter(Context context) {
        mContext = context;
        this.mImageLoader = new GlideImageLoaderImpl(context);
    }

    @Override
    public ColorImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_color_image, null);
        return new ColorImageHolder(v);
    }

    @Override
    public void onBindViewHolder(ColorImageHolder holder, int position) {
        holder.setColor(mColorList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mColorList.size();
    }

    public int selectColor(Color color) {
        if (mColorList == null) {
            return -1;
        }
        mCurrentSelectionPosition = mColorList.indexOf(color);
        notifyDataSetChanged();
        return mCurrentSelectionPosition;
    }

    public void setColorList(List<Color> colorList) {
        mColorList = colorList;
        notifyDataSetChanged();
    }

    public void addColor(Color color) {
        if(color.getColorImagePath().trim().isEmpty()) {
            return;
        }
        mColorList.add(color);
        notifyDataSetChanged();
    }

    public Color getSelectedColor() {
        if(mCurrentSelectionPosition <0 ) {
            return new Color();
        }
        return mColorList.get(mCurrentSelectionPosition);
    }

    class ColorImageHolder extends RecyclerView.ViewHolder {
        FrameLayout layout;
        ImageButton colorImage;
        TextView textColor;
        Color color;

        public ColorImageHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectColor(color);
                }
            });
            layout = (FrameLayout) itemView.findViewById(R.id.color_selector);
            colorImage = (ImageButton) itemView.findViewById(R.id.color_image);
            textColor = (TextView) itemView.findViewById(R.id.txt_color);
        }

        public void setColor(Color color, int position) {
            this.color = color;
            mImageLoader.loadImage(color.getColorImagePath(), colorImage, R.drawable.loading);
            if (position == mCurrentSelectionPosition) {
                layout.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_blue_dark));
            } else {
                layout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            }
            textColor.setText(color.getColorName());
        }
    }
}
