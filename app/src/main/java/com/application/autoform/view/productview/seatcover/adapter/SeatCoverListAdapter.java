package com.application.autoform.view.productview.seatcover.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * Created by sandeep.g9 on 10/25/2016.
 */

public class SeatCoverListAdapter extends RecyclerView.Adapter<SeatCoverListAdapter.SeatCoverItemHolder> {
    Context context;
    List<Product> mSeatCovers = new ArrayList<>();
    ImageLoader mImageLoader = null;

    public SeatCoverListAdapter(Context context) {
        this.context = context;
        mImageLoader = new GlideImageLoaderImpl(context);
    }

    @Override
    public SeatCoverItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_cover_list_item, null);
        return new SeatCoverItemHolder(v);
    }

    @Override
    public void onBindViewHolder(SeatCoverItemHolder holder, int position) {
        holder.setContent(mSeatCovers.get(position));
    }

    @Override
    public int getItemCount() {
        return mSeatCovers.size();
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

    class SeatCoverItemHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView description;
        TextView textPrice;
        Product cover;
        // TextView amount;
        // Button btnCheckout;
        public SeatCoverItemHolder(View itemView) {
            super(itemView);
            // amount = (TextView) itemView.findViewById(R.id.amount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), SeatCoverDetailActivity.class);
                    //TODO: make static final this string DESIGN
                    i.putExtra("DESIGN", cover.getDesignID());
                    v.getContext().startActivity(i);
                }
            });
            description = (TextView) itemView.findViewById(R.id.description);
            coverImage = (ImageView) itemView.findViewById(R.id.icon);
            textPrice = (TextView) itemView.findViewById(R.id.txt_price);
            //  btnCheckout = (Button) itemView.findViewById(R.id.btn_checkout);
        }

        void setContent(Product cover) {
            this.cover = cover;
            description.setText(cover.getDesignName());
            if (cover.getMajorMinorList().size() > 0)
                mImageLoader.loadImage(cover.getMajorMinorList().get(0).getDesignImage(), coverImage, R.drawable.loading);
            textPrice.setText(String.format(textPrice.getContext().getResources().getString(R.string.rs), cover.getMinimumPrice() + "-" + cover.getMaximumPrice()));
        }
    }
}
