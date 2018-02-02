package com.application.autoform.model.cart;

import com.application.autoform.model.bean.User;

/**
 * Created by sandeep.g9 on 3/22/2017.
 */

public interface IUserWishListIntractor {
    public void submitWishListforUser(OnUserWishListUplaoded listUplaoded, User user);

    void submitContactUsInfo(OnContactUsSubmitted listUplaoded, User user);

    public interface OnUserWishListUplaoded {
        public void onUserWishListUploadedSuccessfully();

        public void onUserWishListUploadFailed(String reason);
    }

    public interface  OnContactUsSubmitted {
        public void onContactUsSubmittedSuccessfully();
        public void onContactUsSubmitFail(String reason);
    }
}
