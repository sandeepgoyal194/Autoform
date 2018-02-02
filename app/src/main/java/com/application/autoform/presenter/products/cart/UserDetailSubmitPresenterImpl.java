package com.application.autoform.presenter.products.cart;

import android.util.Patterns;

import com.application.autoform.model.bean.User;
import com.application.autoform.model.cart.CartManager;
import com.application.autoform.model.cart.IUserWishListIntractor;
import com.application.autoform.model.cart.UserWishListIntractorImpl;
import com.application.autoform.view.cart.IUserDetailSubmitView;

/**
 * Created by sandeep.g9 on 3/21/2017.
 */

public class UserDetailSubmitPresenterImpl implements IUserSubmitDetailPresenter, IUserWishListIntractor.OnUserWishListUplaoded {
    IUserDetailSubmitView mView;

    public UserDetailSubmitPresenterImpl(IUserDetailSubmitView mView) {
        this.mView = mView;
    }

    public boolean validate() {
        if (checkNameValid()) {
            if (checkEmailValid()) {
                if (checkMobileValid()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onSubmitClick() {
        if (validate()) {
            User user = new User();
            user.setEmailID(mView.getEmailId());
            user.setMobileNo(mView.getMobileNo());
            user.setName(mView.getName());
            IUserWishListIntractor intractor = new UserWishListIntractorImpl();
            mView.showProgress();
            intractor.submitWishListforUser(this, user);
        }
    }

    private boolean checkNameValid() {
        if (mView.getName().trim().isEmpty()) {
            mView.setNameError("Name should not be blank");
            return false;
        }
        return true;
    }

    private boolean checkMobileValid() {
        if (mView.getMobileNo().trim().isEmpty()) {
            mView.setMobileError("Mobile number should not be blank");
            return false;
        } else if (mView.getMobileNo().trim().length() != 10) {
            mView.setMobileError("Name should not be blank");
            return false;
        }
        return true;
    }

    private boolean checkEmailValid() {
        String emailId = mView.getEmailId();
        if (!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            mView.setEmailIdError("Not a Valid Email");
            return false;
        }
        return true;
    }

    public void clearView() {
        mView.setMobileNo("");
        mView.setName("");
        mView.setEmailId("");
    }

    @Override
    public void onUserWishListUploadedSuccessfully() {
        mView.hideProgress();
        clearView();
        CartManager.getInstance().clearCart();
        mView.finish();
    }

    @Override
    public void onUserWishListUploadFailed(String reason) {
        mView.hideProgress();
        mView.showErroMessage(reason);
    }
}
