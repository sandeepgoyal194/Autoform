package com.application.autoform.model;

import com.application.autoform.model.bean.Accessories;

import java.util.ArrayList;

/**
 * Created by madhurigupta on 11/01/17.
 */

public interface IAccessoriesListIntractor {

    void getAccessoriesList(OnAccessoriesListLoaded onAccessoriesListLoaded);

    interface OnAccessoriesListLoaded {
        void setAccessoriesList(ArrayList<Accessories> accessories);
    }
}
