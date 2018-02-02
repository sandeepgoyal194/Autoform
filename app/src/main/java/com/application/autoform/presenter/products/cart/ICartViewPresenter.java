package com.application.autoform.presenter.products.cart;

import com.application.autoform.model.bean.Product;
import com.application.autoform.model.bean.WishListProduct;

import java.util.List;

/**
 * Created by sandeep.g9 on 3/13/2017.
 */

public interface ICartViewPresenter {
    public void onCartViewStart();
    public void onCartViewStop();

    public interface  ICartView {
        public void setProductList(List<WishListProduct> mCartProducts);
    }

    public interface ICartViewAddPresenter {
        public void addProductToCart(WishListProduct product);
    }
    public interface ICartViewRemovePresenter {
        public void removeProductFromCart(WishListProduct product);
    }
}
