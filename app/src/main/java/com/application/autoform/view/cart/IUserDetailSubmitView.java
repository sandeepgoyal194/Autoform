package com.application.autoform.view.cart;

/**
 * Created by sandeep.g9 on 3/21/2017.
 */

public interface IUserDetailSubmitView  {
    public String getName();
    public String getEmailId();
    public String getMobileNo();
    public void setName(String name);
    public void setEmailId(String emailId);
    public void setMobileNo(String mobileNo);
    public void setMobileError(String message);
    public void setEmailIdError(String message);
    public void setNameError(String message);
    public void showProgress();
    public void hideProgress();
    public void showSuccessFullMessage();
    public void showErroMessage(String message);
    public void finish();
}
