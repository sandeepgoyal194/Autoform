package com.application.autoform.model.products.seatcover;

import com.application.autoform.model.bean.Car;
import com.application.autoform.model.bean.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 26/10/2016.
 */

public interface ISeatCoverIntractor {
    void getSeatCovers(OnSeatCoverDetailLoaded listener, int design);

    void getSeatCovers(OnSeatCoverDetailLoaded listener, Car vehicle);

    interface OnSeatCoverDetailLoaded {
        void setSeatCovers(ArrayList<Product> seatCovers);
    }
}
