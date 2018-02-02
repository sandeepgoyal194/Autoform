package com.application.autoform.model.products.seatcover;

import com.application.autoform.model.bean.Product;

import java.util.List;

/**
 * Created by Sandeep on 24/10/2016.
 */

public interface IPopularDesignSeatCoverIntractor {
    void getPopularSeatCovers(onPopularDesignSeatCoverLoaded listener);

    public interface onPopularDesignSeatCoverLoaded {
        void setPopularDesignSeatCovers(List<Product> seatCovers);
    }
}
