package com.application.autoform.model.products.seatcover;

import com.application.autoform.model.bean.Product;

import java.util.List;

/**
 * Created by sandeep.g9 on 10/25/2016.
 */

public interface ISeatCoverListIntractor {
    void getSeatCovers(onSeatCoverListLoaded listener);

    interface onSeatCoverListLoaded {
        void setSeatCovers(List<Product> seatCovers);
    }
}
