package com.application.autoform.presenter.products.cart;

import com.application.autoform.model.bean.User;
import com.application.autoform.model.cart.IUserWishListIntractor;
import com.application.autoform.model.cart.UserWishListIntractorImpl;
import com.application.autoform.view.cart.IUserDetailSubmitView;

/**
 * Created by Sandeep on 04/04/2017.
 */

public class ContactUsPresenterImpl extends UserDetailSubmitPresenterImpl implements IUserWishListIntractor.OnContactUsSubmitted {

    public ContactUsPresenterImpl(IUserDetailSubmitView mView) {
        super(mView);
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
            intractor.submitContactUsInfo(this, user);
        }
    }

    @Override
    public void onContactUsSubmittedSuccessfully() {
        mView.hideProgress();
        clearView();
        mView.showSuccessFullMessage();
    }

    @Override
    public void onContactUsSubmitFail(String reason) {
        mView.hideProgress();
        mView.showErroMessage(reason);
    }
}
