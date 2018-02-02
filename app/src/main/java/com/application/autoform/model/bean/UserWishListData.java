package com.application.autoform.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 3/22/2017.
 */

public class UserWishListData {
    private String customerName;
    private String emailID;
    private String mobileNo;

    private List<WishListProduct> design = new ArrayList<>();
    public String getName() {
        return customerName;
    }

    public void setName(String name) {
        this.customerName = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public List<WishListProduct> getmWishListProducts() {
        return design;
    }

    public void setmWishListProducts(List<WishListProduct> mWishListProducts) {
        this.design = mWishListProducts;
    }


}
