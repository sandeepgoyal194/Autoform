package com.application.autoform.model.cars;

import com.application.autoform.model.bean.Car;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Sandeep on 06/11/2016.
 */

public class CarVarientListIntractorImpl implements ICarVairentListIntractor {
    private static List<Car> mCars = new ArrayList<>();

    @Override
    public void getCarList(final OnCarLoadingCompleted listener, final String brand) {
        WebServicesWrapper.getInstance().getModelList(brand, new ResponseResolver<ArrayList<Car>>() {
            @Override
            public void onSuccess(ArrayList<Car> cars, Response response) {
                mCars.clear();
                mCars = cars;
                listener.setCarList(mCars);
            }

            @Override
            public void onFailure(RestError error, String msg) {

            }
        });
//        List<NameValuePair> nameValuePair = new ArrayList<>();
//        nameValuePair.add(new BasicNameValuePair("make", brand));
//        ServerManager.getInstance().requestDataFromServer("VehicleInfo", new IServerResponseListener() {
//                    @Override
//                    public void onResponse(String response) {
//                        mCars.clear();
//                        mCars.addAll(Arrays.asList(GsonFactory.getGson().fromJson(response, Car[].class)));
//                        for (Car car : mCars) {
//                            car.setmCarCompany(brand);
//                        }
//                        listener.setCarList(mCars);
//                    }
//
//                    @Override
//                    public void onErrorResponse(int errorType, String response) {
//
//                    }
//                }
//                , nameValuePair, IServerResponseListener.REQUEST_POST);
    }
}
