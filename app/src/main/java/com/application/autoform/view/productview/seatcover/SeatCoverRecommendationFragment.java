package com.application.autoform.view.productview.seatcover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.application.autoform.R;
import com.application.autoform.model.bean.Car;
import com.application.autoform.model.bean.Product;
import com.application.autoform.model.dbhandler.PrefManager;
import com.application.autoform.networknew.imageloader.GlideImageLoaderImpl;
import com.application.autoform.presenter.products.seatcovers.SeatCoverPresenterImpl;
import com.application.autoform.presenter.products.seatcovers.SubCategoryPresenterImpl;

import java.util.ArrayList;

/**
 * Created by Sandeep on 04/11/2016.
 */

public class SeatCoverRecommendationFragment extends SeatCoverDetailFragment {

    RemoveFragmentCommand mRemoveCommand;

    /* Need to add abstract class for both Detail and RecommendationView */
    public static SeatCoverRecommendationFragment newInstance() {
        SeatCoverRecommendationFragment fragment = new SeatCoverRecommendationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cover_design_detail, container, false);
        init(view);
        setAdapter();
        mImageLoader = new GlideImageLoaderImpl(getContext());
        mPresneter = new SeatCoverPresenterImpl(this);
        Car vehicle = new PrefManager(getContext()).getVehicle();
        if (vehicle != null && vehicle.isSelected()) {
            mPresneter.getSeatCover(vehicle);
        } else {
            launcheVehicleSelection();
        }
        mSeatCoverPager.addOnPageChangeListener(pageChangeListener);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRemoveCommand = (RemoveFragmentCommand) context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            String modelName = data.getExtras().getString("ModelName");
            String varient = data.getExtras().getString("Varient");
            Car car = new Car();
            car.setVarient(varient);
            car.setModelName(modelName);
            new PrefManager(getContext()).setVehicle(car);
            mPresneter.getSeatCover(car);
        } else {
            if ((new PrefManager(getContext()).getVehicle()) == null) {
                mRemoveCommand.removeFragment(this);
            }

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.activity_menu_recommendation_activity, menu);
    }

    @Override
    public void setSeatCoverImages(ArrayList<Product> seatCover) {
        if (seatCover == null || (seatCover.size() == 0)) {
            mRemoveCommand.removeFragment(this);
            new PrefManager(getContext()).clearVehicle();
            Snackbar.make(mSeatCoverPager, "No Recommendation Found for your Vehicle", Snackbar.LENGTH_LONG).show();
        } else {
            super.setSeatCoverImages(seatCover);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.vehicle) {
            launcheVehicleSelection();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }


    public interface RemoveFragmentCommand {
        public void removeFragment(Fragment fragment);
    }
}
