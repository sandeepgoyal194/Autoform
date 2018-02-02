package com.application.autoform.view.whyus;

import com.application.autoform.model.bean.WhyUsPageData;

import java.util.List;

/**
 * Created by Sandeep on 05/04/2017.
 */

public interface IWhyUsView {
    public void loadingData();
    public void loadingComplete();
    public void setWhyUsData(List<WhyUsPageData> whyUsData);
    public void onLoadingFail();
}
