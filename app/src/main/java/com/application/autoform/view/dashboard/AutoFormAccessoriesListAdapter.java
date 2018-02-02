package com.application.autoform.view.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.autoform.ApplicationConfiguration;
import com.application.autoform.R;
import com.application.autoform.model.bean.Accessories;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;
import com.application.autoform.view.IItemSelectedListener;
import com.application.autoform.view.productview.AutoFromSeatCoverAdvertiserActivity;
import com.application.autoform.view.productview.seatcover.SeatCoverFullListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public class AutoFormAccessoriesListAdapter extends RecyclerView.Adapter<AutoFormAccessoriesListAdapter.AccessoryItem> {
    private List<Accessories> mAccessoriesList = new ArrayList<>();
    private ImageLoader mImageLoader = null;
    private Context mContext;

    public AutoFormAccessoriesListAdapter(Context context, IItemSelectedListener iItemSelectedListener) {
        mImageLoader = new GlideImageLoaderImpl(context);
        mContext = context;
    }

    @Override
    public AccessoryItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.accessory_list_item, null, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        ));
        return new AccessoryItem(v);
    }

    @Override
    public void onBindViewHolder(AccessoryItem holder, int position) {
        holder.setContent(mAccessoriesList.get(position), position);
    }

    public void setAccessoriesList(List<Accessories> accessories) {
        this.mAccessoriesList = accessories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mAccessoriesList.size();
    }

    int mScreenWidth;

    public int getScreenWidth() {
        if (mScreenWidth == 0) {
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            mScreenWidth = size.x;
        }
        return mScreenWidth;
    }

    class AccessoryItem extends RecyclerView.ViewHolder {
        private ImageView accessoryImage;
        private View itemView;
        private TextView accessoryName;

        public AccessoryItem(View itemView) {
            super(itemView);
            this.itemView = itemView;
            accessoryImage = (ImageView) itemView.findViewById(R.id.image);
            accessoryName = (TextView) itemView.findViewById(R.id.txt_accessory_name);
        }

        void setContent(final Accessories accessories, final int position) {
            int width = getScreenWidth();
           if (position == 0) {
            } else {
                width = width / 2;
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = null;
                    ApplicationConfiguration.getInstance().setmAccessoryType(accessories.getAccessoryName());
                    if (position == 0) {
                        ApplicationConfiguration.getInstance().setmShouldShowAdvertiserPage(true);
                        i = new Intent(mContext, AutoFromSeatCoverAdvertiserActivity.class);
                    } else {
                        i = new Intent(mContext, SeatCoverFullListActivity.class);
                    }
                    mContext.startActivity(i);
                }
            });
            //itemView.getLayoutParams().width = width;
            itemView.getLayoutParams().height = width;
            mImageLoader.loadImage(accessories.getAccessoryImagePath(), accessoryImage, R.drawable.loading);
            accessoryName.setText(accessories.getAccessoryName());
        }


    }
}
