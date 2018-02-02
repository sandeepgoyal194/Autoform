package com.application.autoform.presenter.products.cart;

import com.application.autoform.model.bean.Product;
import com.application.autoform.model.bean.WishListProduct;
import com.application.autoform.model.cart.CartManager;
import com.application.autoform.model.cart.ICartManager;

import java.util.List;

/**
 * Created by sandeep.g9 on 3/14/2017.
 */

public class CartViewPresenterImpl implements ICartViewPresenter, ICartManager.CartUpdateListener, ICartViewPresenter.ICartViewAddPresenter, ICartViewPresenter.ICartViewRemovePresenter {
    ICartView cartView;

    public CartViewPresenterImpl(ICartView cartView) {
        this.cartView = cartView;
    }

    public CartViewPresenterImpl() {
    }

    @Override
    public void onCartViewStart() {
        CartManager.getInstance().getCartProducts(this);
    }

    @Override
    public void onCartViewStop() {
    }

    @Override
    public void onCartUpdate(List<WishListProduct> productList) {
        if (cartView != null)
            cartView.setProductList(productList);
    }

    @Override
    public void addProductToCart(WishListProduct product) {
        CartManager.getInstance().addToCart(product);
    }

    @Override
    public void removeProductFromCart(WishListProduct product) {
        CartManager.getInstance().removeFromCart(product);
    }
}
