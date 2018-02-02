package com.application.autoform.model.products.seatcover;

import com.application.autoform.ApplicationConfiguration;
import com.application.autoform.model.bean.Product;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by sandeep.g9 on 10/25/2016.
 */

public class SeatCoverListIntractorImpl implements ISeatCoverListIntractor {
    @Override
    public void getSeatCovers(final onSeatCoverListLoaded listener) {
        List<Product> coverList = SeatCoverManager.getInstance().getmSeatCovers();
        if (coverList != null && !coverList.isEmpty()) {
            listener.setSeatCovers(coverList);
        } else {
            WebServicesWrapper.getInstance().getSeatCovers("ALL", "", new ResponseResolver<ArrayList<Product>>() {
                @Override
                public void onSuccess(ArrayList<Product> oldSeatCovers, Response response) {
                    listener.setSeatCovers(oldSeatCovers);
                    SeatCoverManager.getInstance().setmSeatCovers(oldSeatCovers);
                }

                @Override
                public void onFailure(RestError error, String msg) {

                }
            }, ApplicationConfiguration.getInstance().getmAccessoryType());
        }
    }
}
