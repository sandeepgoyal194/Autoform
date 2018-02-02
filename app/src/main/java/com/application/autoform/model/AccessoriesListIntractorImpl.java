package com.application.autoform.model;

import com.application.autoform.model.bean.Accessories;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by madhurigupta on 11/01/17.
 */

public class AccessoriesListIntractorImpl implements IAccessoriesListIntractor {

    @Override
    public void getAccessoriesList(final OnAccessoriesListLoaded onAccessoriesListLoaded) {
        WebServicesWrapper.getInstance().getAccessoriesList(new ResponseResolver<ArrayList<Accessories>>() {
            @Override
            public void onSuccess(ArrayList<Accessories> accessories, Response response) {
                onAccessoriesListLoaded.setAccessoriesList(accessories);
            }

            @Override
            public void onFailure(RestError error, String msg) {

            }
        });
    }
}
