package com.application.autoform.presenter.products.seatcovers;

import com.application.autoform.model.bean.SubCategory;
import com.application.autoform.model.products.seatcover.ISubCategoryIntractor;
import com.application.autoform.model.products.seatcover.SubCategoryIntractorImpl;
import com.application.autoform.view.productview.seatcover.ISubCategoryView;

import java.util.List;

/**
 * Created by Sandeep on 07/05/2017.
 */

public class SubCategoryPresenterImpl implements ISubCategoryPresenter, ISubCategoryIntractor.OnSubCategoryLoaded {
    ISubCategoryView mView;
    static List<SubCategory> mSubCategoryList;

    public SubCategoryPresenterImpl(ISubCategoryView mView) {
        this.mView = mView;
    }

    static boolean subCategoryLoading;

    @Override
    public void onViewFocus() {
        subCategoryLoading = true;
        ISubCategoryIntractor intractor = new SubCategoryIntractorImpl();
        intractor.getSubCategories(this);
    }

    @Override
    public void getSubCategory(String subCategory) {
        if (mSubCategoryList != null && mSubCategoryList.size() > 0) {
            SubCategory subCategory1 = new SubCategory();
            subCategory1.setSubCategoryName(subCategory);
            int index = mSubCategoryList.indexOf(subCategory1);
            if (index >= 0) {
                mView.setSubCategoryDetail(mSubCategoryList.get(index));
            }
        } else {
            if (!subCategoryLoading) {
                onViewFocus();
            }
        }
    }


    @Override
    public void onSubCategoryLoadedSuccess(List<SubCategory> subCategoryList) {
        subCategoryLoading = false;
        mSubCategoryList = subCategoryList;
    }

    @Override
    public void onSubCategoryLoadFail() {
        subCategoryLoading = false;
    }
}
