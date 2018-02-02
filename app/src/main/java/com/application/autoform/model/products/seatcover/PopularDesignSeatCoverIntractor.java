package com.application.autoform.model.products.seatcover;

import com.application.autoform.ApplicationConfiguration;
import com.application.autoform.model.bean.Product;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by Sandeep on 24/10/2016.
 */

public class PopularDesignSeatCoverIntractor implements IPopularDesignSeatCoverIntractor {
    @Override
    public void getPopularSeatCovers(final IPopularDesignSeatCoverIntractor.onPopularDesignSeatCoverLoaded listener) {
        WebServicesWrapper.getInstance().getSeatCovers("POPULARITY", "", new ResponseResolver<ArrayList<Product>>() {
            @Override
            public void onSuccess(ArrayList<Product> oldSeatCovers, Response response) {
                listener.setPopularDesignSeatCovers(oldSeatCovers);
            }

            @Override
            public void onFailure(RestError error, String msg) {

            }
        }, ApplicationConfiguration.getInstance().getmAccessoryType());
    }
}
