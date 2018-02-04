package com.application.autoform.presenter.products.seatcovers;

import com.application.autoform.model.bean.Product;
import com.application.autoform.model.products.seatcover.ISeatCoverListIntractor;
import com.application.autoform.model.products.seatcover.SeatCoverListIntractorImpl;
import com.application.autoform.view.productview.seatcover.ISeatCoverListView;

import java.util.List;

/**
 * Created by sandeep.g9 on 10/25/2016.
 */

public class SeatCoverListPresenterImpl implements ISeatCoverListPresenter, ISeatCoverListIntractor.onSeatCoverListLoaded {
    private ISeatCoverListView mView;
    private ISeatCoverListIntractor mIntractor;

    public SeatCoverListPresenterImpl(ISeatCoverListView view) {
        mView = view;
    }

    @Override
    public void setSeatCovers(List<Product> seatCovers) {
        if(mView == null) {
            return;
        }
        mView.setAllDesignSeatCover(seatCovers);
    }

    @Override
    public void getSeatCoverDesignList() {
        mIntractor = new SeatCoverListIntractorImpl();
        mIntractor.getSeatCovers(this);
    }
}
