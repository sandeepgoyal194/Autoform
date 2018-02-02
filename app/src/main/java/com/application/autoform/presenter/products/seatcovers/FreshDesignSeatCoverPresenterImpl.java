package com.application.autoform.presenter.products.seatcovers;

import com.application.autoform.model.bean.Product;
import com.application.autoform.model.products.seatcover.FreshDesignSeatCoverIntractorImpl;
import com.application.autoform.model.products.seatcover.IFreshDesignSeatCoverIntractor;
import com.application.autoform.view.productview.seatcover.IFreshDesignSeatCoverView;

import java.util.List;

/**
 * Created by Sandeep on 22/10/2016.
 */

public class FreshDesignSeatCoverPresenterImpl implements IFreshDesignSeatCoverPresenter, IFreshDesignSeatCoverIntractor.onFreshDesignSeatCoverLoaded {
    IFreshDesignSeatCoverIntractor mIFreshDesignSeatCoverIntractor;
    IFreshDesignSeatCoverView mView;

    public FreshDesignSeatCoverPresenterImpl(IFreshDesignSeatCoverView mView) {
        this.mView = mView;
    }

    @Override
    public void getFreshSeatCovers() {
        mIFreshDesignSeatCoverIntractor = new FreshDesignSeatCoverIntractorImpl();
        mIFreshDesignSeatCoverIntractor.getFreshSeatCovers(this);
    }

    @Override
    public void setFreshDesignSeatCovers(List<Product> seatCovers) {
        mView.setFreshSeatCovers(seatCovers);
    }
}
