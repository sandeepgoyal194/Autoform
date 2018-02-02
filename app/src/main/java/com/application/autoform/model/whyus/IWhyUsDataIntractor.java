package com.application.autoform.model.whyus;

import com.application.autoform.model.bean.WhyUsPageData;

import java.util.List;

/**
 * Created by Sandeep on 05/04/2017.
 */

public interface IWhyUsDataIntractor {
    public void getWhyUsData(onWhyUsDataLoaded dataLoaded);

    public interface onWhyUsDataLoaded {
        public void onWhyUsDataLoadedSuccessfully(List<WhyUsPageData> whyUsData);

        public void onWhyDataLoadFail();
    }
}
