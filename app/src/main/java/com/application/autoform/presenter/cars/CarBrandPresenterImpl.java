package com.application.autoform.presenter.cars;

import com.application.autoform.model.bean.CarBrand;
import com.application.autoform.model.cars.CarBrandIntractorImpl;
import com.application.autoform.model.cars.ICarBrandIntractor;
import com.application.autoform.view.cars.ICarBrandListView;

import java.util.List;

/**
 * Created by Sandeep on 03/11/2016.
 */

public class CarBrandPresenterImpl implements ICarBrandPresenter, ICarBrandIntractor.OnBrandLoaded {
    ICarBrandListView mView;
    ICarBrandIntractor mIntractor;

    public CarBrandPresenterImpl(ICarBrandListView view) {
        mView = view;

    }

    @Override
    public void getCarBrandList() {
        mIntractor = new CarBrandIntractorImpl();
        mIntractor.getCarBrandList(this);
    }

    @Override
    public void setCarBrandList(List<CarBrand> brandList) {
        mView.setCarBrandList(brandList);
    }
}
