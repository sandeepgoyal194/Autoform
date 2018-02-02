package com.application.autoform.model.cart;

import com.application.autoform.model.bean.Product;
import com.application.autoform.model.bean.WishListProduct;

import java.util.List;

/**
 * Created by sandeep.g9 on 3/13/2017.
 */

public interface ICartManager {
    public void addToCart( WishListProduct product);

    public void removeFromCart(WishListProduct product);

    public void clearCart();

    public interface CartUpdateListener {
        public void onCartUpdate(List<WishListProduct> productList);
    }

}
