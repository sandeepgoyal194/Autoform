package com.application.autoform.view.cars;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.autoform.R;
import com.application.autoform.model.bean.CarBrand;
import com.application.autoform.presenter.cars.CarBrandPresenterImpl;
import com.application.autoform.presenter.cars.ICarBrandPresenter;

import java.util.List;

/**
 * Created by sandeep.g9 on 10/19/2016.
 */

public class CarBrandListFragment extends Fragment implements ICarBrandListView {
    RecyclerView mBrandList;
    CarBrandListAdapter mAdapter;
    ICarBrandPresenter mPresenter;
    OnBrandSelected mBrandSelectedListener;

    public static CarBrandListFragment newInstance() {
        CarBrandListFragment fragment = new CarBrandListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_brand, container, false);
        mPresenter = new CarBrandPresenterImpl(this);
        setAdapter(view);
        mPresenter.getCarBrandList();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBrandSelectedListener = (OnBrandSelected) context;
    }

    private void setAdapter(View view) {
        mBrandList = (RecyclerView) view.findViewById(R.id.car_brand_list);
        mAdapter = new CarBrandListAdapter(getContext(), mBrandSelectedListener);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mBrandList.setLayoutManager(manager);
        mBrandList.setAdapter(mAdapter);
    }

    @Override
    public void setCarBrandList(List<CarBrand> carBrandList) {
        mAdapter.setCarBrands(carBrandList);
    }

    public interface OnBrandSelected {
        public void setBrand(String brand);
    }
}
