package com.application.autoform.presenter.products.seatcovers;

import com.application.autoform.model.bean.Product;
import com.application.autoform.model.products.seatcover.IPopularDesignSeatCoverIntractor;
import com.application.autoform.model.products.seatcover.ISeatCoverListIntractor;
import com.application.autoform.model.products.seatcover.PopularDesignSeatCoverIntractor;
import com.application.autoform.model.products.seatcover.SeatCoverListIntractorImpl;
import com.application.autoform.view.productview.seatcover.IPopularSeatCoverView;

import java.util.List;

/**
 * Created by Sandeep on 24/10/2016.
 */

public class PopularDesignSeatCoverPresenterImpl implements IPopularDesignSeatCoverPresenter, IPopularDesignSeatCoverIntractor.onPopularDesignSeatCoverLoaded {
    IPopularDesignSeatCoverIntractor mPopularDesignSeatCoverIntractor;
    IPopularSeatCoverView mView;

    public PopularDesignSeatCoverPresenterImpl(IPopularSeatCoverView mView) {
        this.mView = mView;
    }

    @Override
    public void getPopularSeatCover() {
        mPopularDesignSeatCoverIntractor = new PopularDesignSeatCoverIntractor();
        new SeatCoverListIntractorImpl().getSeatCovers(new ISeatCoverListIntractor.onSeatCoverListLoaded() {
            @Override
            public void setSeatCovers(List<Product> seatCovers) {

            }
        });
        mPopularDesignSeatCoverIntractor.getPopularSeatCovers(this);
    }

    @Override
    public void setPopularDesignSeatCovers(List<Product> seatCovers) {
        mView.setPopularSeatCover(seatCovers);
    }
}
