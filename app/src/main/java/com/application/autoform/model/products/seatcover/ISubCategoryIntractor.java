package com.application.autoform.model.products.seatcover;

import com.application.autoform.model.bean.SubCategory;

import java.util.List;

/**
 * Created by Sandeep on 07/05/2017.
 */

public interface ISubCategoryIntractor {
    public void getSubCategories(OnSubCategoryLoaded subCategoryLoaded);


    public interface OnSubCategoryLoaded {
        public void onSubCategoryLoadedSuccess(List<SubCategory> subCategoryList);

        public void onSubCategoryLoadFail();
    }

}
