package com.application.autoform.presenter.dealers;

import com.application.autoform.model.bean.Dealer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public interface IDealerListPresenter {
    void getDealerList();

    ArrayList<Dealer> getmDealerList();

    public interface IDealerFilterPresenter {
        String[] getStateList();

        ArrayList<String> getCityList(String state);


    }
}
