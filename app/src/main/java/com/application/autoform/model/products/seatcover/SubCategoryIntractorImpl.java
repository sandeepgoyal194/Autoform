package com.application.autoform.model.products.seatcover;

import com.application.autoform.model.bean.SubCategory;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Sandeep on 07/05/2017.
 */

public class SubCategoryIntractorImpl implements ISubCategoryIntractor {


    @Override
    public void getSubCategories(final OnSubCategoryLoaded subCategoryLoaded) {
            WebServicesWrapper.getInstance().getSubCategories(new ResponseResolver<List<SubCategory>>() {
                @Override
                public void onSuccess(List<SubCategory> subCategories, Response response) {
                    subCategoryLoaded.onSubCategoryLoadedSuccess(subCategories);
                }

                @Override
                public void onFailure(RestError error, String msg) {
                    subCategoryLoaded.onSubCategoryLoadFail();
                }
            });
        }
    }
