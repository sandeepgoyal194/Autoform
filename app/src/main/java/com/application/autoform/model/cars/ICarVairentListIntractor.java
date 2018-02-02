package com.application.autoform.model.cars;

import com.application.autoform.model.bean.Car;

import java.util.List;

/**
 * Created by Sandeep on 06/11/2016.
 */

public interface ICarVairentListIntractor {

    void getCarList(OnCarLoadingCompleted listener, String brand);

    interface OnCarLoadingCompleted {
        void setCarList(List<Car> cars);
    }
}
