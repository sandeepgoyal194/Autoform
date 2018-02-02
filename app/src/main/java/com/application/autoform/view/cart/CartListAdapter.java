package com.application.autoform.view.cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.autoform.R;
import com.application.autoform.model.bean.WishListProduct;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.networknew.imageloader.ImageLoader;
import com.application.autoform.presenter.products.cart.CartViewPresenterImpl;
import com.application.autoform.presenter.products.cart.ICartViewPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 3/14/2017.
 */

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartItemHolder> {
    List<WishListProduct> mCartProducts = new ArrayList();
    CartRemoveListener mListener;

    public CartListAdapter(CartRemoveListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public CartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_view, parent, false);
        return new CartItemHolder(v);
    }

    public void setmCartProducts(List<WishListProduct> cartProducts) {
        this.mCartProducts = cartProducts;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CartItemHolder holder, int position) {
        holder.setOnView(mCartProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mCartProducts.size();
    }

    class CartItemHolder extends RecyclerView.ViewHolder {
        WishListProduct mProduct;
        ImageView imgProduct;
        ImageView imgMajor;
        ImageView imgMinor;
        TextView mProductName;
        TextView mMajorColorName;
        TextView mMinorColorName;
        LinearLayout mRemoveButton;
        private ImageLoader mImageLoader = null;
        ICartViewPresenter.ICartViewRemovePresenter mPresenter;

        public CartItemHolder(View itemView) {
            super(itemView);
            imgProduct = (ImageView) itemView.findViewById(R.id.img_product);
            imgMajor = (ImageView) itemView.findViewById(R.id.img_major);
            imgMinor = (ImageView) itemView.findViewById(R.id.img_minor);
            mProductName = (TextView) itemView.findViewById(R.id.txt_product_name);
            mImageLoader = new GlideImageLoaderImpl(itemView.getContext());
            mRemoveButton = (LinearLayout) itemView.findViewById(R.id.btn_remove);
            mMajorColorName = (TextView) itemView.findViewById(R.id.major_color_image_name);
            mMinorColorName = (TextView) itemView.findViewById(R.id.minor_color_image_name);
            mPresenter = new CartViewPresenterImpl();
            mRemoveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.removeProductFromCart(mProduct);
                    mListener.onCartItemRemove();

                }
            });
        }

        void setOnView(WishListProduct product) {
            mProduct = product;
            mImageLoader.loadImage(product.getProductDesginPath(), imgProduct, R.drawable.loading);
            if (product.getMinorColorPath() != null && !product.getMinorColorPath().isEmpty()) {
                mImageLoader.loadImage(product.getMinorColorPath(), imgMinor, R.drawable.loading);
                mMinorColorName.setText(product.getMinorColor());
            } else {
                mMinorColorName.setVisibility(View.GONE);
                imgMinor.setVisibility(View.GONE);
            }
            if (product.getMajorColorPath() != null && !product.getMajorColorPath().isEmpty()) {
                mImageLoader.loadImage(product.getMajorColorPath(), imgMajor, R.drawable.loading);
                mMajorColorName.setText(product.getMajorColor());
            } else {
                mMinorColorName.setVisibility(View.GONE);
                imgMajor.setVisibility(View.GONE);
            }
            mProductName.setText(product.getDesignName());
        }
    }

    interface CartRemoveListener {
        void onCartItemRemove();
    }
}
