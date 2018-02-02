package com.application.autoform.presenter.products.seatcovers;

import com.application.autoform.model.bean.Car;
import com.application.autoform.model.bean.Product;
import com.application.autoform.model.products.seatcover.ISeatCoverIntractor;
import com.application.autoform.model.products.seatcover.SeatCoverIntractorImpl;
import com.application.autoform.view.productview.seatcover.ISeatCoverDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 10/25/2016.
 */

public class SeatCoverPresenterImpl implements ISeatCoverPresenter, ISeatCoverIntractor.OnSeatCoverDetailLoaded {

    ISeatCoverDetailView mView;
    ISeatCoverIntractor mIntractor;

    public SeatCoverPresenterImpl(ISeatCoverDetailView view) {
        mView = view;
    }

    @Override
    public void getSeatCover(int design) {
        mIntractor = new SeatCoverIntractorImpl();
        mIntractor.getSeatCovers(this, design);
    }

    @Override
    public void getSeatCover(Car vehicle) {
        mIntractor = new SeatCoverIntractorImpl();
        mIntractor.getSeatCovers(this, vehicle);
    }

    @Override
    public void setSeatCovers(ArrayList<Product> product) {
        mView.setSeatCoverImages(product);
    }

}
