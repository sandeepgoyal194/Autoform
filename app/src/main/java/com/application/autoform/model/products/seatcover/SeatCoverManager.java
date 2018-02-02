package com.application.autoform.model.products.seatcover;

import com.application.autoform.ApplicationConfiguration;
import com.application.autoform.model.bean.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sandeep on 30/10/2016.
 */

public class SeatCoverManager {
    private static SeatCoverManager mManager = new SeatCoverManager();
    HashMap<String, List<Product>> mProducts = new HashMap<>();

    public static SeatCoverManager getInstance() {
        return mManager;
    }

    public List<Product> getmSeatCovers() {
        return mProducts.get(ApplicationConfiguration.getInstance().getmAccessoryType());
    }

    public void setmSeatCovers(List<Product> mSeatCovers) {
   /*     List<Product> seatCovers = mProducts.get(ApplicationConfiguration.getInstance().getmAccessoryType());
        if (seatCovers != null) {
            for (Product seatCover : seatCovers) {
                mSeatCovers.remove(seatCover);
            }
        } else {
            seatCovers = new ArrayList<>();
        }
        seatCovers.addAll(mSeatCovers);*/
        mProducts.put(ApplicationConfiguration.getInstance().getmAccessoryType(), mSeatCovers);
    }

    public ArrayList<Product> getSeatCover(int design) {
        List<Product> mSeatCovers = getmSeatCovers();
        ArrayList<Product> products = null;
        if (mSeatCovers != null) {
            products = new ArrayList<>();
            for (Product product : mSeatCovers) {
                if (product.getDesignID() == design) {
                    products.add(product);
                }
            }
        }
        return products;
    }
}
