package com.application.autoform.model.dealer;

import com.application.autoform.model.bean.Dealer;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public class DealerListIntractorImpl implements IDealerListIntractor {
    private static List<Dealer> mDealerList = new ArrayList<>();

    @Override
    public void getDealerList(final OnDealerListLoaded listener) {
        WebServicesWrapper.getInstance().getDealersList(new ResponseResolver<ArrayList<Dealer>>() {
            @Override
            public void onSuccess(ArrayList<Dealer> dealers, Response response) {
                mDealerList.clear();
                mDealerList = dealers;
                listener.setDealerList(mDealerList);
            }

            @Override
            public void onFailure(RestError error, String msg) {

            }
        });
//        List<NameValuePair> nameValuePair = new ArrayList<>();
//        ServerManager.getInstance().requestDataFromServer("DealerInfo", new IServerResponseListener() {
//                    @Override
//                    public void onResponse(String response) {
//                        mDealerList.clear();
//                        mDealerList.addAll(Arrays.asList(GsonFactory.getGson().fromJson(response, Dealer[].class)));
//                        listener.setDealerList(mDealerList);
//                    }
//
//                    @Override
//                    public void onErrorResponse(int errorType, String response) {
//
//                    }
//                }
//                , nameValuePair, IServerResponseListener.REQUEST_GET);
    }
}
