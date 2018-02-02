package com.application.autoform.presenter.dealers;

import com.application.autoform.model.bean.Dealer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sandeep on 19/11/17.
 */

public class DealerListFilterPresenter implements IDealerListPresenter.IDealerFilterPresenter {


    List<Dealer> dealerList;


    public DealerListFilterPresenter(List<Dealer> dealerList) {
        this.dealerList = dealerList;
        updateStateCityList();
    }
    HashMap<String, ArrayList<String>> mStateCityMap = new HashMap<>();
    private void updateStateCityList() {
        if (dealerList != null) {
            for (Dealer d : dealerList) {
                String state = d.getmState();
                if (mStateCityMap.containsKey(state)) {
                    ArrayList<String> city = mStateCityMap.get(state);
                    if (!city.contains((String) d.getmState()))
                        city.add(d.getmCity());
                } else {
                    ArrayList<String> city = new ArrayList<>();
                    city.add(d.getmCity());
                    mStateCityMap.put(state, city);
                }
            }
        }
    }

    public String[] getStateList() {
        return mStateCityMap.keySet().toArray(new String[mStateCityMap.size()]);
    }

    public ArrayList<String> getCityList(String state) {
        return mStateCityMap.get(state);
    }

}
