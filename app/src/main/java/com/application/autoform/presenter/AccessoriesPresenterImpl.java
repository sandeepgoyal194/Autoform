package com.application.autoform.presenter;

import com.application.autoform.model.AccessoriesListIntractorImpl;
import com.application.autoform.model.IAccessoriesListIntractor;
import com.application.autoform.model.bean.Accessories;
import com.application.autoform.view.dashboard.IAccessoriesView;

import java.util.ArrayList;

/**
 * Created by madhurigupta on 11/01/17.
 */

public class AccessoriesPresenterImpl implements IAccessoriesListPresenter, IAccessoriesListIntractor.OnAccessoriesListLoaded {

    private IAccessoriesView mView;
    private IAccessoriesListIntractor mIntractor;

    public AccessoriesPresenterImpl(IAccessoriesView accessoriesView) {
        mView = accessoriesView;
    }

    @Override
    public void getAccessoriesList() {
        mIntractor = new AccessoriesListIntractorImpl();
        mIntractor.getAccessoriesList(this);
    }

    @Override
    public void setAccessoriesList(ArrayList<Accessories> accessories) {
        if (mView != null) {
            mView.setAccessoriesList(accessories);
        }
    }
}
