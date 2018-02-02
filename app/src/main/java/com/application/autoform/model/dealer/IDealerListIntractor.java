package com.application.autoform.model.dealer;

import com.application.autoform.model.bean.Dealer;

import java.util.List;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public interface IDealerListIntractor {
    void getDealerList(OnDealerListLoaded listener);

    interface OnDealerListLoaded {
        void setDealerList(List<Dealer> mDealerList);
    }
}
