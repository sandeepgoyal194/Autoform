package com.application.autoform.presenter.dealers;

import com.application.autoform.model.bean.Dealer;
import com.application.autoform.model.dealer.DealerListIntractorImpl;
import com.application.autoform.model.dealer.IDealerListIntractor;
import com.application.autoform.view.dealers.IDealerListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public class DealerListPresenterImpl implements IDealerListPresenter, IDealerListIntractor.OnDealerListLoaded {
    private IDealerListView mView;
    private IDealerListIntractor mIntractor;

    ArrayList<Dealer> mDealerList;
    public DealerListPresenterImpl() {
    }

    public DealerListPresenterImpl(IDealerListView mView) {
        this.mView = mView;
    }

    @Override
    public void getDealerList() {
        mIntractor = new DealerListIntractorImpl();
        mIntractor.getDealerList(this);
    }

    @Override
    public void setDealerList(List<Dealer> mDealerList) {
        mView.setDealerList(mDealerList);
        this.mDealerList = (ArrayList<Dealer>) mDealerList;
    }

    public ArrayList<Dealer> getmDealerList(){
        return mDealerList;
    }
}