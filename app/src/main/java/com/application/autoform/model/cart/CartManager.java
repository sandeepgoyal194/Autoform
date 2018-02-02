package com.application.autoform.model.cart;

import com.application.autoform.model.bean.Product;
import com.application.autoform.model.bean.WishListProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 3/13/2017.
 */

public class CartManager implements ICartManager{
    private static final CartManager ourInstance = new CartManager();
    ICartManager.CartUpdateListener mListener;
    public static CartManager getInstance() {
        return ourInstance;
    }
    List<WishListProduct> mProducts = new ArrayList<>();

    private CartManager() {
    }

    public void addToCart(WishListProduct product) {
        mProducts.add(product);
        if(mListener != null) {
            mListener.onCartUpdate(mProducts);
        }

    }

    @Override
    public void removeFromCart(WishListProduct product) {
        mProducts.remove(product);
        if(mListener != null) {
            mListener.onCartUpdate(mProducts);
        }
    }

    public void getCartProducts(ICartManager.CartUpdateListener listener) {
        mListener = listener;
        if (mListener != null) {
            mListener.onCartUpdate(mProducts);
        }
    }
    @Override
    public void clearCart() {
        mProducts.clear();
        if(mListener != null) {
            mListener.onCartUpdate(mProducts);
        }
    }

}
