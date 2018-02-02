package com.application.autoform.view.cart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.application.autoform.R;
import com.application.autoform.presenter.products.cart.IUserSubmitDetailPresenter;
import com.application.autoform.presenter.products.cart.UserDetailSubmitPresenterImpl;
import com.application.autoform.view.AutoFormActivity;

/**
 * Created by sandeep.g9 on 3/16/2017.
 */

public class ActivityUserFillInfo extends AutoFormActivity implements IUserDetailSubmitView {
    EditText mUserName;
    EditText mUserMobileNo;
    EditText mUserEmailId;
    View mSubmitButton;
    IUserSubmitDetailPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mUserEmailId = (EditText) findViewById(R.id.edtEmailId);
        mUserMobileNo = (EditText) findViewById(R.id.edtMobileNo);
        mUserName = (EditText) findViewById(R.id.edtName);
        mSubmitButton = findViewById(R.id.submit);
        mPresenter = new UserDetailSubmitPresenterImpl(this);
        setmToolbar();
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onSubmitClick();
            }
        });

    }

    @Override
    public String getName() {
        return mUserName.getText().toString();
    }

    @Override
    public String getEmailId() {
        return mUserEmailId.getText().toString();
    }

    @Override
    public String getMobileNo() {
        return mUserMobileNo.getText().toString();
    }

    @Override
    public void setName(String name) {
        mUserName.setText(name);
    }

    @Override
    public void setEmailId(String emailId) {
         mUserEmailId.setText(emailId);
    }

    @Override
    public void setMobileNo(String mobileNo) {
        mUserMobileNo.setText(mobileNo);
    }


    @Override
    public void setMobileError(String message) {
        mUserMobileNo.setError(message);
        mUserMobileNo.requestFocus();
    }

    @Override
    public void setEmailIdError(String message) {
        mUserEmailId.setError(message);
        mUserEmailId.requestFocus();
    }

    @Override
    public void setNameError(String message) {
        mUserName.setError(message);
        mUserName.requestFocus();
    }

    ProgressDialog mProgressDialog;

    @Override
    public void showProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Sending Info");
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void showSuccessFullMessage() {
        setResult(1);
        Toast.makeText(this,"Wishlist Sent Sucessfully",Toast.LENGTH_LONG).show();
    }


    @Override
    public void showErroMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public void finish() {
        super.finish();
    }
}
