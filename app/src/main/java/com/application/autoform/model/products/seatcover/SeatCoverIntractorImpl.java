package com.application.autoform.model.products.seatcover;

import com.application.autoform.ApplicationConfiguration;
import com.application.autoform.model.bean.Car;
import com.application.autoform.model.bean.OldSeatCover;
import com.application.autoform.model.bean.Product;
import com.application.autoform.network.IServerResponseListener;
import com.application.autoform.network.ServerManager;
import com.application.autoform.networknew.GsonFactory;
import com.application.autoform.networknew.ResponseResolver;
import com.application.autoform.networknew.RestError;
import com.application.autoform.networknew.WebServicesWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import retrofit2.Response;

/**
 * Created by Sandeep on 27/10/2016.
 */

//This class for particular seat cover, for all list pls check SeatCoverListIntractor
public class SeatCoverIntractorImpl implements ISeatCoverIntractor, ISeatCoverListIntractor.onSeatCoverListLoaded {
    OnSeatCoverDetailLoaded mListener;
    int mDesign;

    @Override
    public void getSeatCovers(OnSeatCoverDetailLoaded listener, int design) {
        ArrayList<Product> seatCover = SeatCoverManager.getInstance().getSeatCover(design);
        if (seatCover == null) {
            new SeatCoverListIntractorImpl().getSeatCovers(this);
            mListener = listener;
            mDesign = design;
        } else {
            listener.setSeatCovers(seatCover);
        }
    }

    @Override
    public void getSeatCovers(final OnSeatCoverDetailLoaded listener, Car vehicle) {
        WebServicesWrapper.getInstance().getRecommendDesign(new ResponseResolver<ArrayList<Product>>() {
            @Override
            public void onSuccess(ArrayList<Product> oldSeatCovers, Response response) {
                listener.setSeatCovers(oldSeatCovers);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                listener.setSeatCovers(null);
            }
        }, vehicle.getModelName(), vehicle.getVarient());
    }


    @Override
    public void setSeatCovers(List<Product> seatCovers) {
        getSeatCovers(mListener, mDesign);
    }
}
