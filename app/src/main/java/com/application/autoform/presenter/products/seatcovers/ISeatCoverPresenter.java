package com.application.autoform.presenter.products.seatcovers;

import com.application.autoform.model.bean.Car;

/**
 * Created by sandeep.g9 on 10/20/2016.
 */

public interface ISeatCoverPresenter {
    void getSeatCover(int design);

    void getSeatCover(Car vehicle);
}
