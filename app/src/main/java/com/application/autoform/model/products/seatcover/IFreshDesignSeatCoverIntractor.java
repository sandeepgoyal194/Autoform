package com.application.autoform.model.products.seatcover;

import com.application.autoform.model.bean.Product;

import java.util.List;

/**
 * Created by Sandeep on 22/10/2016.
 */

public interface IFreshDesignSeatCoverIntractor {
    void getFreshSeatCovers(onFreshDesignSeatCoverLoaded listener);

    interface onFreshDesignSeatCoverLoaded {
        void setFreshDesignSeatCovers(List<Product> seatCovers);
    }
}
