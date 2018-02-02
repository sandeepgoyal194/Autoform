package com.application.autoform.view.productview.seatcover.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.Product;
import com.application.autoform.model.bean.SubCategory;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;
import com.application.autoform.presenter.products.seatcovers.ISubCategoryPresenter;
import com.application.autoform.presenter.products.seatcovers.SubCategoryPresenterImpl;
import com.application.autoform.view.productview.seatcover.ISubCategoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 26/10/2016.
 */

public class SeatCoverImageAdapter extends PagerAdapter {
    static ImageLoader mImageLoader;
    Context mContext;
    List<Product.MajorMinorList> mSeatCoverImages = new ArrayList<>();

    public SeatCoverImageAdapter(Context mContext) {
        this.mContext = mContext;
        mImageLoader = new GlideImageLoaderImpl(mContext);
    }

    @Override
    public int getCount() {
        if (mSeatCoverImages.size() > 0)
            return mSeatCoverImages.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.product_image, container, false);
        SeatCoverImageAdapter.ViewHolder holder = new SeatCoverImageAdapter.ViewHolder(itemView);
        holder.setContent(mSeatCoverImages.get(position));
        container.addView(itemView);
        return itemView;
    }

    public List<Product.MajorMinorList> getmSeatCovers() {
        return mSeatCoverImages;
    }

    public void setmSeatCovers(List<Product.MajorMinorList> mSeatCoverImages) {
        this.mSeatCoverImages = mSeatCoverImages;
        notifyDataSetChanged();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }

    public Product.MajorMinorList getCurrentMajorMinor(int currentIndex) {
        return mSeatCoverImages.get(currentIndex);
    }

    private class ViewHolder implements ISubCategoryView {
        ImageView productImage;
        ImageView subCategoryImage;
        TextView price;
        ISubCategoryPresenter presenter;

        ViewHolder(View itemView) {
            productImage = (ImageView) itemView.findViewById(R.id.img_product);
            subCategoryImage = (ImageView) itemView.findViewById(R.id.img_subcategory);
            price = (TextView) itemView.findViewById(R.id.txt_price);
            presenter = new SubCategoryPresenterImpl(this);
        }

        int getLayoutID() {
            return R.layout.product_image;
        }

        void setContent(Product.MajorMinorList majorMinorList) {
            mImageLoader.loadImage(majorMinorList.getDesignImage(), productImage, R.drawable.loading);
            presenter.getSubCategory(majorMinorList.getSubCategory());

            price.setText(String.format(productImage.getContext().getResources().getString(R.string.rs), majorMinorList.getMinimumPrice() + "-" + majorMinorList.getMaximumPrice()));
        }

        @Override
        public void setSubCategoryDetail(final SubCategory subCategoryDetail) {
            subCategoryImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
// ...Irrelevant code for customizing the buttons and title
                    LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                    View view = inflater.inflate(R.layout.sub_categorydetail,null);
                    ImageView image = (ImageView) view.findViewById(R.id.sub_category_image);
                    TextView textView = (TextView) view.findViewById(R.id.sub_category_detail);
                    mImageLoader.loadImage(subCategoryDetail.getImage(), image);
                    textView.setText(subCategoryDetail.getDescription());
                    dialogBuilder.setView(view);
                    dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();
                }
            });
            mImageLoader.loadImage(subCategoryDetail.getImage(), subCategoryImage);
        }
    }

}

