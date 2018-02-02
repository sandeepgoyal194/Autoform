package com.application.autoform.presenter.cars;

import com.application.autoform.model.bean.Car;
import com.application.autoform.model.cars.CarVarientListIntractorImpl;
import com.application.autoform.model.cars.ICarVairentListIntractor;
import com.application.autoform.view.cars.ICarVarientListView;

import java.util.List;

/**
 * Created by Sandeep on 06/11/2016.
 */

public class CarVarientListPresenterImpl implements ICarVarientListPresenter, ICarVairentListIntractor.OnCarLoadingCompleted {
    ICarVarientListView mView;
    ICarVairentListIntractor mIntractor;

    public CarVarientListPresenterImpl(ICarVarientListView view) {
        mView = view;
    }

    @Override
    public void getCarVarients(String brand) {
        mIntractor = new CarVarientListIntractorImpl();
        mIntractor.getCarList(this, brand);
    }

    @Override
    public void setCarList(List<Car> cars) {
        mView.setCarVarientList(cars);
    }
}
