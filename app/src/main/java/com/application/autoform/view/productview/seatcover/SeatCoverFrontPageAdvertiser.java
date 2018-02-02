package com.application.autoform.view.productview.seatcover;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.autoform.R;
import com.application.autoform.model.bean.Product;
import com.application.autoform.presenter.products.seatcovers.FreshDesignSeatCoverPresenterImpl;
import com.application.autoform.presenter.products.seatcovers.IFreshDesignSeatCoverPresenter;
import com.application.autoform.presenter.products.seatcovers.IPopularDesignSeatCoverPresenter;
import com.application.autoform.presenter.products.seatcovers.PopularDesignSeatCoverPresenterImpl;
import com.application.autoform.view.productview.seatcover.custom_layout.AdvertisementBanner;

import java.util.List;

public class SeatCoverFrontPageAdvertiser extends Fragment implements IFreshDesignSeatCoverView, IPopularSeatCoverView {


    AdvertisementBanner mPopularDesignBanner;
    AdvertisementBanner mFreshDesignBanner;
    IFreshDesignSeatCoverPresenter mFreshDesignSeatCoverPresenter;
    IPopularDesignSeatCoverPresenter mPopularDesignSeatCoverPresenter;


    public SeatCoverFrontPageAdvertiser() {
        mFreshDesignSeatCoverPresenter = new FreshDesignSeatCoverPresenterImpl(this);
        mPopularDesignSeatCoverPresenter = new PopularDesignSeatCoverPresenterImpl(this);
    }

    // TODO: Rename and change types and number of parameters
    public static SeatCoverFrontPageAdvertiser newInstance() {
        SeatCoverFrontPageAdvertiser fragment = new SeatCoverFrontPageAdvertiser();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seat_cover__front, container, false);
        mFreshDesignBanner = (AdvertisementBanner) view.findViewById(R.id.banner_fresh_design);
        mPopularDesignBanner = (AdvertisementBanner) view.findViewById(R.id.banner_popular_design);
        mFreshDesignSeatCoverPresenter.getFreshSeatCovers();
        mPopularDesignSeatCoverPresenter.getPopularSeatCover();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setFreshSeatCovers(List<Product> seatCovers) {
        if(seatCovers.size() == 0) {
            return;
        }
        mFreshDesignBanner.setmSeatCovers(seatCovers);
    }

    @Override
    public void setPopularSeatCover(List<Product> seatCovers) {
        if(seatCovers.size() == 0) {
            return;
        }
        mPopularDesignBanner.setmSeatCovers(seatCovers);
    }
}
