package com.application.autoform.presenter.products.whyus;

import com.application.autoform.model.bean.WhyUsPageData;
import com.application.autoform.model.whyus.IWhyUsDataIntractor;
import com.application.autoform.model.whyus.WhyUsDataIntractorImpl;
import com.application.autoform.view.whyus.IWhyUsView;

import java.util.List;

/**
 * Created by Sandeep on 05/04/2017.
 */

public class WhyUsPresenterImpl implements IWhyUsPresenter, IWhyUsDataIntractor.onWhyUsDataLoaded {
    IWhyUsView mView;

    public WhyUsPresenterImpl(IWhyUsView mView) {
        this.mView = mView;
    }

    @Override
    public void onViewStarted() {
        IWhyUsDataIntractor dataIntractor = new WhyUsDataIntractorImpl();
        mView.loadingData();
        dataIntractor.getWhyUsData(this);
    }

    @Override
    public void onViewStopped() {
        mView = null;
    }

    @Override
    public void onWhyUsDataLoadedSuccessfully(List<WhyUsPageData> whyUsData) {
        if(mView != null) {
            mView.loadingComplete();
            mView.setWhyUsData(whyUsData);
        }
    }

    @Override
    public void onWhyDataLoadFail() {
        if(mView != null) {
            mView.loadingComplete();
        }
    }
}
