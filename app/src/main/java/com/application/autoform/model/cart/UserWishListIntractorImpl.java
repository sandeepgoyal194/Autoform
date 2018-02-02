package com.application.autoform.model.cart;

import com.application.autoform.model.bean.User;
import com.application.autoform.model.bean.UserWishListData;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import retrofit2.Response;

/**
 * Created by sandeep.g9 on 3/22/2017.
 */

public class UserWishListIntractorImpl implements IUserWishListIntractor {
    @Override
    public void submitWishListforUser(final OnUserWishListUplaoded listUplaoded, User user) {
        UserWishListData userWishListData = new UserWishListData();
        userWishListData.setName(user.getName());
        userWishListData.setMobileNo(user.getMobileNo());
        userWishListData.setEmailID(user.getEmailID());
        userWishListData.setmWishListProducts(CartManager.getInstance().mProducts);
        WebServicesWrapper.getInstance().submitWishList(new ResponseResolver<com.application.autoform.model.bean.Response>() {

            @Override
            public void onSuccess(com.application.autoform.model.bean.Response response, Response response2) {
                if (response.getStatus().isEmpty()) {
                    listUplaoded.onUserWishListUploadFailed(response.getError());
                } else {
                    listUplaoded.onUserWishListUploadedSuccessfully();
                }
            }

            @Override
            public void onFailure(RestError error, String msg) {
                listUplaoded.onUserWishListUploadFailed(msg);
            }
        }, userWishListData);
    }

    @Override
    public void submitContactUsInfo(final OnContactUsSubmitted listUplaoded, User user) {
        WebServicesWrapper.getInstance().contactusSubmit(new ResponseResolver<com.application.autoform.model.bean.Response>() {

            @Override
            public void onSuccess(com.application.autoform.model.bean.Response response, Response response2) {
                if (response.getStatus().isEmpty()) {
                    listUplaoded.onContactUsSubmitFail(response.getError());
                } else {
                    listUplaoded.onContactUsSubmittedSuccessfully();
                }
            }

            @Override
            public void onFailure(RestError error, String msg) {
                listUplaoded.onContactUsSubmitFail(msg);
            }
        }, user);
    }
}
