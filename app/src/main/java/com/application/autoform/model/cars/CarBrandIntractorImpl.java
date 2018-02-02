package com.application.autoform.model.cars;

import com.application.autoform.model.bean.CarBrand;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Sandeep on 03/11/2016.
 */

public class CarBrandIntractorImpl implements ICarBrandIntractor {
    private List<CarBrand> mBrandList = new ArrayList<>();

    @Override
    public void getCarBrandList(final OnBrandLoaded listener) {
        WebServicesWrapper.getInstance().getMakeList(new ResponseResolver<ArrayList<CarBrand>>() {
            @Override
            public void onSuccess(ArrayList<CarBrand> carBrands, Response response) {
                mBrandList.clear();
                mBrandList = carBrands;
                listener.setCarBrandList(mBrandList);
            }

            @Override
            public void onFailure(RestError error, String msg) {

            }
        });
//        ServerManager.getInstance().requestDataFromServer("SelectVehicleModel", new IServerResponseListener() {
//                    @Override
//                    public void onResponse(String response) {
//                        mBrandList.clear();
//                        mBrandList.addAll(Arrays.asList(GsonFactory.getGson().fromJson(response, CarBrand[].class)));
//                        listener.setCarBrandList(mBrandList);
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
