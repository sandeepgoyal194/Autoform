package com.application.autoform.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.application.autoform.R;
import com.application.autoform.presenter.products.cart.ContactUsPresenterImpl;
import com.application.autoform.presenter.products.cart.IUserSubmitDetailPresenter;
import com.application.autoform.view.cart.IUserDetailSubmitView;

/**
 * Created by Sandeep on 03/04/2017.
 */

public class ContactUsFragment extends Fragment implements IUserDetailSubmitView {
    EditText mUserName;
    EditText mUserMobileNo;
    EditText mUserEmailId;
    View mSubmitButton;
    IUserSubmitDetailPresenter mPresenter;
    public static ContactUsFragment newInstance() {
        ContactUsFragment fragment = new ContactUsFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_info, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        mUserEmailId = (EditText) view.findViewById(R.id.edtEmailId);
        mUserMobileNo = (EditText) view.findViewById(R.id.edtMobileNo);
        mUserName = (EditText) view.findViewById(R.id.edtName);
        mSubmitButton = view.findViewById(R.id.submit);
        mPresenter = new ContactUsPresenterImpl(this);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onSubmitClick();
            }
        });
        return view;
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
        mProgressDialog = new ProgressDialog(getContext());
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
        Toast.makeText(getContext(), "Contact Sent Sucessfully", Toast.LENGTH_LONG).show();
    }


    @Override
    public void showErroMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finish() {

    }
}
