package com.application.autoform.model.whyus;

import com.application.autoform.ApplicationConfiguration;
import com.application.autoform.model.bean.WhyUsPageData;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Sandeep on 05/04/2017.
 */

public class WhyUsDataIntractorImpl implements IWhyUsDataIntractor {
    List<WhyUsPageData> whyUsPageDatas;

    @Override
    public void getWhyUsData(final onWhyUsDataLoaded dataLoaded) {
        if (whyUsPageDatas != null && whyUsPageDatas.size() > 0) {
            dataLoaded.onWhyUsDataLoadedSuccessfully(whyUsPageDatas);
            return;
        }
        WebServicesWrapper.getInstance().whyUsPageDataCall(new ResponseResolver<List<WhyUsPageData>>() {

            @Override
            public void onSuccess(List<WhyUsPageData> whyUsPageDatas, Response response) {
                WhyUsDataIntractorImpl.this.whyUsPageDatas = whyUsPageDatas;
                dataLoaded.onWhyUsDataLoadedSuccessfully(whyUsPageDatas);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                dataLoaded.onWhyDataLoadFail();
            }
        }, ApplicationConfiguration.getInstance().getmAccessoryType());
    }

}
