package com.application.autoform.model.cars;

import com.application.autoform.model.bean.CarBrand;

import java.util.List;

/**
 * Created by Sandeep on 03/11/2016.
 */

public interface ICarBrandIntractor {
    public void getCarBrandList(OnBrandLoaded listener);

    public interface OnBrandLoaded {
        public void setCarBrandList(List<CarBrand> brandList);
    }
}
